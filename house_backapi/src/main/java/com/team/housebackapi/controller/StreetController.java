package com.team.housebackapi.controller;

import com.team.housebackapi.entity.Street;
import com.team.housebackapi.service.StreetService;
import com.team.housebackapi.utils.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/street/")
public class StreetController {

    @Autowired
    private StreetService streetService;

    @RequestMapping("getStreetData")
    public BaseResult getStreetData(Integer did){
        List<Street> list = streetService.getAllStreetByDid(did);
        return new BaseResult(200,list);
    }
}
