package com.project.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

import com.project.model.Stock;
import com.project.model.User;
import com.project.model.Wallet;
import com.project.service.StockService;
import com.project.service.UserService;

@Component
public class UniValidator {

    @Autowired
    private UserService userService;

    @Autowired
    private StockService stockService;

    public boolean validateBuyStockData(HttpServletRequest request, User user, Stock stock) {
        setBuyAttributes(request, stock);
        if (isEmpty(request.getParameter("amount"))) {
            setError(request, "No value entered!");
            return true;
        } else if (!isNumeric(request.getParameter("amount"))) {
            setError(request, "Wrong value.");
            return true;
        }
        Integer amount = Integer.valueOf(request.getParameter("amount"));

        if (isNonPositive(amount)) {
            setError(request, "Enter a value greater than zero.");
            return true;
        } else if (!stockService.isMultipleOfUnit(amount, stock.getUnit())) {
            setError(request, "You can buy only multiples of this stock (in this case multiples of the " + stock.getUnit() + " number).");
            return true;
        } else if (isEnough(amount, stock.getAmount())) {
            setError(request, "Stock has only " + stock.getAmount() + " actions to buy.");
            return true;
        } else if (stock.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(amount)).compareTo(user.getMoney()) > 0) {
            setError(request, "You don't have enough money to buy. You have " + user.getMoney() + " PLN.");
            return true;
        }
        return false;
    }

    public boolean validateSellStockData(HttpServletRequest request, Stock stock, Wallet wallet) {
        setSellAttributes(request, stock, wallet);
        if (isEmpty(request.getParameter("amount"))) {
            setError(request, "No value entered!");
            return true;
        } else if (!isNumeric(request.getParameter("amount"))) {
            setError(request, "Wrong value.");
            return true;
        }
        Integer amount = Integer.valueOf(request.getParameter("amount"));

        if (isNonPositive(amount)) {
            setError(request, "Enter a value greater than zero.");
            return true;
        } else if (isEnough(amount, wallet.getAmount())) {
            setError(request, "You don't have enough amount of this stock to sell. You have " + wallet.getAmount() + " actions.");
            return true;
        }
        return false;
    }

    public boolean validateUpdateMoney(HttpServletRequest request) {
        setUpdateMoneyAttributes(request);
        if (isEmpty(request.getParameter("money"))) {
            setError(request, "No value entered!");
            return true;
        } else if (!isNumeric(request.getParameter("money"))) {
            setError(request, "Wrong value.");
            return true;
        }
        BigDecimal money = new BigDecimal(request.getParameter("money").replaceAll(",", ""));
        if (money.compareTo(BigDecimal.ZERO) < 0) {
            setError(request, "Enter a non-negative value.");
            return true;
        }
        return false;
    }

    private boolean isEmpty(String text) {
        return text.isEmpty();
    }

    private boolean isNumeric(String text) {
        return StringUtils.isNumeric(text);
    }

    private boolean isNonPositive(Integer amount) {
        return amount <= 0;
    }

    private boolean isEnough(Integer amount, Integer amountToCompare) {
        return amount > amountToCompare;
    }

    private void setError(HttpServletRequest request, String error) {
        request.setAttribute("error", error);
    }

    private void setUpdateMoneyAttributes(HttpServletRequest request) {
        request.setAttribute("mode", "MODE_USER_MONEY");
        request.setAttribute("user", userService.getCurrentUser());
        request.setAttribute("money", request.getParameter("money"));
    }

    private void setBuyAttributes(HttpServletRequest request, Stock stock) {
        request.setAttribute("mode", "MODE_BUY");
        request.setAttribute("user", userService.getCurrentUser());
        request.setAttribute("stock", stock);
        request.setAttribute("amount", request.getParameter("amount"));
    }

    private void setSellAttributes(HttpServletRequest request, Stock stock, Wallet wallet) {
        request.setAttribute("mode", "MODE_SELL");
        request.setAttribute("user", userService.getCurrentUser());
        request.setAttribute("stock", stock);
        request.setAttribute("wallet", wallet);
        request.setAttribute("amount", request.getParameter("amount"));
    }
}
