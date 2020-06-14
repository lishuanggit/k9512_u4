package com.team.housebackapi.service;

import com.github.pagehelper.PageInfo;
import com.team.housebackapi.entity.House;
import com.team.housebackapi.entity.HouseCondition;
import com.team.housebackapi.utils.PageParmeter;

public interface HouseService {

    //发布出租房
    public int addHouse(House house);

    //查询某用户下出租房
    public PageInfo<House> getHouseByUser(Integer userid, PageParmeter pageParmeter);

    //删除出租房
    public int deleteHouse(String id,Integer delState);//1表示删除，0表示恢复

    public PageInfo<House> getBrowserHouse(HouseCondition houseCondition);
}
