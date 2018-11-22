package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.model.Stock;
import com.project.service.UserService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.project.model.StockItem;
import com.project.searcher.StockSearcher;
import com.project.service.StockService;
import com.project.service.WalletService;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private StockService stockService;

    @Autowired
    private WalletService walletService;

    @Autowired
    private StockSearcher stockSearcher;

    @GetMapping("/")
    public String home(HttpServletRequest request) {
        String JSONFromHTTP = stockService.getJSONFromHTTP();
        List<StockItem> responseFromHTTPList = stockService.convertResponseJSONToList(JSONFromHTTP);
        List<Stock> stocksFromWebsiteList = stockService.getAllStocksFromList(responseFromHTTPList);
        List<Stock> stocksFromDatabaseList = stockSearcher.findAll();
        String publicationDate = responseFromHTTPList.get(0).getPublicationDate();
        stockService.createUpdateAndRoundPrcies(stocksFromDatabaseList, stocksFromWebsiteList);
        walletService.roundValue();

        request.setAttribute("mode", "MODE_HOME");
        request.setAttribute("stocks", stockSearcher.findAll());
        request.setAttribute("wallet", userService.getCurrentUser().getUserWallet());
        request.setAttribute("user", userService.getCurrentUser());
        request.setAttribute("date", publicationDate.substring(0, publicationDate.indexOf("T")).replaceAll("-", "."));
        request.setAttribute("time", publicationDate.substring(publicationDate.indexOf("T") + 1, publicationDate.indexOf(".")));
        return "index";
    }
}
