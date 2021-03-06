package cn.itsource.aigou.service.impl;

import cn.itsource.aigou.domain.ProductType;
import cn.itsource.aigou.mapper.ProductTypeMapper;
import cn.itsource.aigou.service.IProductTypeService;
import cn.itsource.basic.util.AjaxResult;
import cn.itsource.common.client.RedisClient;
import cn.itsource.common.client.StaticPageClient;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品目录 服务实现类
 * </p>
 *
 * @author solargen
 * @since 2019-07-31
 */
@Service
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductType> implements IProductTypeService {


    @Autowired
    //由于依赖的openfeign，会创建接口的动态代理对象交给spring管理
    private RedisClient redisClient;
    @Autowired
    private StaticPageClient staticPageClient;

    /**
     * 生成主页面过程
     * 先根据product.type.vm生成一个product.type.vm.html
     * 再根据home.vm生成主页面
     */
    @Override
    public void genHomePage() {
        //第一步 ： 生成product.type.vm.html
        Map<String,Object> map = new HashMap<>();
        //模板路径
        String templatePath = "E:/projectUpload/aigou-parent/aigou-product-parent/aigou-product-service/src/main/resources/template/product.type.vm";
        //生成的静态页面路径
        String targetPath = "E:/ideaProject/aigou-parent/aigou-product-parent/aigou-product-service/src/main/resources/template/product.type.vm.html";
        //model 就是List 存放所有的商品类型
        List<ProductType> productTypes = loadTypeTree();
        map.put("model",productTypes);
        map.put("templatePath",templatePath);
        map.put("targetPath",targetPath);
        staticPageClient.genStaticPage(map);

        //第二步 ： 生成home.html
        map = new HashMap<>();
        //模板路径
        templatePath = "E:/projectUpload/aigou-parent/aigou-product-parent/aigou-product-service/src/main/resources/template/home.vm";
        //生成的静态页面路径（前端项目路径）
        targetPath = "";
        //model 中要有一个数据是staticRoot
        Map<String,String> model = new HashMap<>();
        model.put("staticRoot","E:/projectUpload/aigou-parent/aigou-product-parent/aigou-product-service/src/main/resources/");
        map.put("model",model);
        map.put("templatePath",templatePath);
        map.put("targetPath",targetPath);

        staticPageClient.genStaticPage(map);

    }

    /**
     * 查询商品类型树
     * @return
     */
    @Override
    public List<ProductType> loadTypeTree() {
        //redis缓存
        //从Redis里面查询缓存数据
        //从redis中获取数据
        AjaxResult result = redisClient.get("productTypes");
        //把结果转为字符串对象
        String productTypesJsonStr = (String) result.getRestObj();
        //把字符串转为list集合
        List<ProductType> productTypes = JSON.parseArray(productTypesJsonStr, ProductType.class);
        //判断是否有值
        if(productTypes==null||productTypes.size()<=0){
            //没有则查询数据库，并将数据缓存到redis中
            productTypes = loop();
            redisClient.set("productTypes",JSON.toJSONString(productTypes));
        }
        //返回数据
        return productTypes;
        //递归方式实现
        //return recursive(0L);
    }

    /**
     * 循环方式
     * @return
     */
    private List<ProductType> loop() {
        //查询出所有品牌类型
        List<ProductType> productTypes = baseMapper.selectList(null);
        //定义一个List存放一级菜单
        List<ProductType> list = new ArrayList<>();
        //定义一个Map存放所有的ProductType，key是id，value是类型对象
        Map<Long,ProductType> map = new HashMap<>();
        for (ProductType pt : productTypes) {
            //把查询的ProductType存入map
            map.put(pt.getId(),pt);
        }
        //循环
        for (ProductType productType : productTypes) {
            if(productType.getPid()==0){//判断是否是一级菜单
                list.add(productType);//存入list
            }else{//下一级菜单
                ProductType parent = map.get(productType.getPid());
                parent.getChildren().add(productType);
            }
        }
        return list;
    }

    /**
     * 递归方式实现加载类型树
     * 缺点：
     * （1）性能很低，要发送多次sql
     * （2）递归的深度可能会导致栈溢出
     *
     * @return
     */
    private List<ProductType> recursive(Long id) {
        //查询所有一级菜单
        List<ProductType> parents = baseMapper.selectList(new QueryWrapper<ProductType>().eq("pid", id));
        for (ProductType parent : parents) {
            //取到所有的子
            List<ProductType> children = recursive(parent.getId());
            if (!children.isEmpty()) {
                parent.setChildren(children);
            }
        }
        return parents;
    }
}
