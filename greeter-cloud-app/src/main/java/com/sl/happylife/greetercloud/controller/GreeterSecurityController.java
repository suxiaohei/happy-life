/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sl.happylife.greetercloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Joe Grandja
 */
@Controller
public class GreeterSecurityController {

    @RequestMapping("/security")
    public String root() {
        return "redirect:/security/index";
    }

    @RequestMapping("/security/user/index")
    public String userIndex() {
        return "/security/user/index";
    }

    @RequestMapping("/security/login")
    public String login() {
        return "/security/login";
    }

    @RequestMapping("/security/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "/security/login";
    }

}