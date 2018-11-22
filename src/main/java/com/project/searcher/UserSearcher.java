package com.project.searcher;

import com.project.model.User;

public interface UserSearcher {

    User findByUsername(String username);
}
