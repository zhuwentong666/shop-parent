package com.baidu.shop.response;

import com.baidu.shop.document.GoodsDoc;
import com.zwt.shop.base.Result;
import com.zwt.shop.entity.BrandEntity;
import com.zwt.shop.entity.CategoryEntity;
import com.zwt.shop.status.HTTPStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName GoodsResponse
 * @Description: TODO
 * @Author zhuwentong
 * @Date 2021/3/6
 * @Version V1.0
 **/
@Data
@NoArgsConstructor
public class GoodsResponse extends Result<List<GoodsDoc>> {
    private Long total;
    private Long totalPage;
    private List<BrandEntity> brandList;
    private List<CategoryEntity> categoryList;
    public GoodsResponse(Long total, Long totalPage, List<BrandEntity>
            brandList, List<CategoryEntity> categoryList, List<GoodsDoc> goodsDocs){
        super(HTTPStatus.OK,HTTPStatus.OK + "",goodsDocs);
        this.total = total;
        this.totalPage = totalPage;
        this.brandList = brandList;
        this.categoryList = categoryList;
    }

}
