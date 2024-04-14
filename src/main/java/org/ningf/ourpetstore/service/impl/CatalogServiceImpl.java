package org.ningf.ourpetstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.ningf.ourpetstore.entity.*;
import org.ningf.ourpetstore.persistence.*;
import org.ningf.ourpetstore.service.CatalogService;
import org.ningf.ourpetstore.vo.CategoryVO;
import org.ningf.ourpetstore.vo.ItemVO;
import org.ningf.ourpetstore.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service("catalogService")
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private ItemQuantityMapper itemQuantityMapper;

    @Override
    public CategoryVO getCategory(String categoryId) {
        CategoryVO categoryVO = new CategoryVO();
        Category category = categoryMapper.selectById(categoryId);

        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category" , categoryId);
        List<Product> productList = productMapper.selectList(queryWrapper);

        categoryVO.setCategoryId(categoryId);
        categoryVO.setCategoryName(category.getCategoryName());
        categoryVO.setDescription(category.getDescription());
        categoryVO.setProductList(productList);

        return categoryVO;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryMapper.selectAllCategories();
    }

    @Override
    public List<Orders> getAllOrders() {
        return ordersMapper.selectAllOrders();
    }

    @Override
    public ProductVO getProduct(String productId) {
        ProductVO productVO = new ProductVO();
        Product product = productMapper.selectById(productId);
        if (product != null) {
            // 如果产品不为空，则继续处理
            QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("productid", productId);
            List<Item> itemList = itemMapper.selectList(queryWrapper);

            productVO.setProductId(productId);
            productVO.setCategoryId(product.getCategoryId()); // 可能导致异常的代码
            productVO.setProductName(product.getProductName());
            productVO.setDescription(product.getDescription());
            productVO.setItemList(itemList);
        } else {
            // 如果产品为空，则设置默认值或者抛出异常
            // 在这里，我们选择抛出异常
            throw new RuntimeException("Product with ID " + productId + " not found");
        }

        return productVO;
    }

    @Override
    public ItemVO getItem(String itemId) {
        ItemVO itemVO = new ItemVO();
        Item item = itemMapper.selectById(itemId);
        Product product = productMapper.selectById(item.getProductId());
        //ItemQuantity itemQuantity = itemQuantityMapper.selectById(itemId);

        itemVO.setItemId(itemId);
        itemVO.setListPrice(item.getListPrice());
        itemVO.setStatus(item.getStatus());
        itemVO.setUnitCost(item.getUnitCost());
        itemVO.setSupplierId(item.getSupplierId());
        itemVO.setAttributes(item.getAttribute1());
        itemVO.setProductId(product.getProductId());
        itemVO.setProductName(product.getProductName());
        String [] temp = product.getDescription().split("\"");
        itemVO.setDescriptionImage(temp[1]);
        itemVO.setDescriptionText(temp[2].substring(1));

        //itemVO.setQuantity(itemQuantity.getQuantity());
        return itemVO;
    }

    @Override
    public boolean createCategory(CategoryVO categoryVO) {
        Category category = new Category();
        if(categoryVO.getCategoryId()==""){
            return false;
        }
        category.setCategoryId(categoryVO.getCategoryId());
        category.setCategoryName(categoryVO.getCategoryName());
        category.setDescription(categoryVO.getDescription());
        int insertResult = categoryMapper.insert(category);
        if(insertResult>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteCategory(String categoryId) {
        // 查询与该类别关联的产品
        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.eq("category", categoryId);
        List<Product> productList = productMapper.selectList(productQueryWrapper);

        // 删除类别
        int categoryDeleteResult = categoryMapper.deleteById(categoryId);
        // 删除产品
        int productDeleteResult = productMapper.delete(productQueryWrapper);
        // 从产品列表中提取产品ID
        List<String> productIds = productList.stream()
                .map(Product::getProductId)
                .collect(Collectors.toList());

        if (productList.size()==0){
            // 检查删除操作结果是否符合预期
            boolean deleteSuccessful = categoryDeleteResult > 0 && productDeleteResult == productList.size();
            return deleteSuccessful;
        }else {
            // 查询与这些产品相关的项目
            QueryWrapper<Item> itemQueryWrapper = new QueryWrapper<>();
            itemQueryWrapper.in("productid", productIds);
            List<Item> itemList = itemMapper.selectList(itemQueryWrapper);
            // 删除项目
            int itemDeleteResult = itemMapper.delete(itemQueryWrapper);
            // 检查删除操作结果是否符合预期
            boolean deleteSuccessful = categoryDeleteResult > 0 && productDeleteResult == productList.size()
                    && itemDeleteResult == itemList.size();
            return deleteSuccessful;
        }


    }

    @Override
    public boolean updateCategory(CategoryVO categoryVO) {
        if (categoryVO.getCategoryId() == "") {
            return false;
        }
        Category category = new Category();
        category.setCategoryId(categoryVO.getCategoryId());
        category.setCategoryName(categoryVO.getCategoryName());
        category.setDescription(categoryVO.getDescription());

        int updateResult = categoryMapper.updateById(category);

        // 检查更新操作是否成功
        if (updateResult > 0) {
            QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("category", categoryVO.getCategoryId());
            List<Product> productList = productMapper.selectList(queryWrapper);
            for (Product product : productList) {
                product.setCategoryId(categoryVO.getCategoryId());
                productMapper.updateById(product);
            }
            return true;
        } else {
            // 更新失败
            return false;
        }
    }


    @Override
    public boolean deleteProduct(ProductVO productVO) {

        // 查询与产品相关的项目列表
        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("productid", productVO.getProductId());
        List<Item> itemList = itemMapper.selectList(queryWrapper);

        // 删除产品
        int a = productMapper.deleteById(productVO.getProductId());

        // 删除与产品相关的项目
        int b = itemMapper.delete(queryWrapper);

        // 判断是否删除成功
        if (a > 0 && b == itemList.size()) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public boolean updateProduct(ProductVO productVO) {
        if (productVO == null) {
            return false;
        }

        // 创建 Product 对象并设置要更新的属性值
        Product product = new Product();
        product.setProductId(productVO.getProductId());
        product.setCategoryId(productVO.getCategoryId());
        product.setProductName(productVO.getProductName());
        product.setDescription(productVO.getDescription());

        // 尝试执行更新操作
        int updateResult = productMapper.updateById(product);

        // 检查更新操作是否成功
        if (updateResult > 0) {
            // 更新成功，继续更新 Item 信息（如果有）
            QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("productid", productVO.getProductId());
            List<Item> itemList = itemMapper.selectList(queryWrapper);
            for (Item item : itemList) {
                item.setProductId(productVO.getProductId());
                itemMapper.updateById(item);
            }
            return true;
        } else {
            // 更新失败
            return false;
        }
    }

    @Override
    public boolean createProduct(ProductVO productVO) {
        Product product = new Product();
        if(productVO.getProductId()==""||productVO.getCategoryId()==""){
            return false;
        }
        product.setProductId(productVO.getProductId());
        product.setCategoryId(productVO.getCategoryId());
        product.setProductName(productVO.getProductName());
        product.setDescription(productVO.getDescription());
        int insertResult = productMapper.insert(product);
        if(insertResult>0){
            return true;
        }else {
            return false;
        }
    }


    @Override
    public boolean deleteItem(ItemVO itemVO) {
        int a=itemMapper.deleteById(itemVO.getItemId());
        if(a>0) return true;
        else return false;
    }

    @Override
    public boolean updateItem(ItemVO itemVO) {
        if(itemVO==null||itemVO.getSupplierId()>2){
            return false;
        }
        Item item=new Item();
        item.setItemId(itemVO.getItemId());
        item.setProductId(itemVO.getProductId());
        item.setListPrice(itemVO.getListPrice());
        item.setUnitCost(itemVO.getUnitCost());
        item.setSupplierId(itemVO.getSupplierId());
        item.setStatus(itemVO.getStatus());
        // 尝试执行更新操作
        int updateResult = itemMapper.updateById(item);
        // 检查更新操作是否成功
        if (updateResult > 0) {
            // 更新成功
            return true;
        } else {
            // 更新失败
            return false;
        }
    }

    @Override
    public boolean createItem(ItemVO itemVO) {
        Item item = new Item();
        if(itemVO.getItemId()==""||itemVO.getProductId()=="" ||itemVO.getSupplierId()>2){
            return false;
        }
        item.setItemId(itemVO.getItemId());
        item.setProductId(itemVO.getProductId());
        item.setListPrice(itemVO.getListPrice());
        item.setUnitCost(itemVO.getUnitCost());
        item.setSupplierId(itemVO.getSupplierId());
        item.setStatus(itemVO.getStatus());
        int insertResult = itemMapper.insert(item);
        if(insertResult>0){
            return true;
        }else {
            return false;
        }
    }
}
