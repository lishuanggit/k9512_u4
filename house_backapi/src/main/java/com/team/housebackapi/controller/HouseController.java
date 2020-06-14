package com.team.housebackapi.controller;

import com.github.pagehelper.PageInfo;
import com.team.housebackapi.entity.House;
import com.team.housebackapi.entity.HouseCondition;
import com.team.housebackapi.entity.Users;
import com.team.housebackapi.service.HouseService;
import com.team.housebackapi.utils.BaseResult;
import com.team.housebackapi.utils.FileUploadUtil;
import com.team.housebackapi.utils.PageParmeter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/house/")
@CrossOrigin(value = "*",allowCredentials ="true")
public class HouseController {

    @Autowired
    private HouseService houseService;

    @RequestMapping("fabuHouse")
    public BaseResult fabuHouse(
            House house,
            @RequestParam(value = "pfile",required = false)
                    MultipartFile pfile, HttpSession session){
        //文件上传
        try {
            String path = "f:\\images";
            String fileName = FileUploadUtil.upload(pfile,path);

            //设置当前所属用户编号
            /*Users users = (Users) session.getAttribute("logininfo");
            house.setUserId(users.getId());*/

            //设置上传文件路径
            house.setPath(fileName);
            //设置随机id
            house.setId(System.currentTimeMillis()+"");
            house.setIsdel(0);//设置为未删除
            houseService.addHouse(house);
            return new BaseResult(BaseResult.RESULT_SUCCESS,"");
        }catch (Exception e){
            e.printStackTrace();
            return new BaseResult(BaseResult.RESULT_FAIL,"发布失败"+e.getMessage());
        }
    }

    //获取用户出租房信息
    @RequestMapping("getHouseByPage")
    public BaseResult getHouseByPage(PageParmeter pageParmeter){
        PageInfo<House> pageInfo = houseService.getHouseByUser(1003, pageParmeter);
        Map<String,Object> map = new HashMap<>();
        map.put("totalPage",pageInfo.getPages());//总页数
        map.put("row",pageInfo.getList());//当前页数据
        return new BaseResult(BaseResult.RESULT_SUCCESS,map);
    }

    //删除出租房
    @RequestMapping("delHouse")
    public BaseResult delHouse(String id){
        int i = houseService.deleteHouse(id, 1);
        if (i>0){
            return new BaseResult(BaseResult.RESULT_SUCCESS);
        }else {
            return new BaseResult(BaseResult.RESULT_FAIL);
        }
    }

    //浏览出租房
    @RequestMapping("searchHouse")
    public BaseResult searchHouse(HouseCondition houseCondition){
        PageInfo<House> pageInfo = houseService.getBrowserHouse(houseCondition);
        Map<String,Object> map = new HashMap<>();
        map.put("curPage",pageInfo.getPageNum());
        map.put("totalPage",pageInfo.getPages());
        map.put("list",pageInfo.getList());
        return new BaseResult(BaseResult.RESULT_SUCCESS,map);
    }
}
