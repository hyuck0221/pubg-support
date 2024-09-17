package com.hshim.pubg_support.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class PageController {
    @GetMapping("/control")
    fun control() = "control.html"
}