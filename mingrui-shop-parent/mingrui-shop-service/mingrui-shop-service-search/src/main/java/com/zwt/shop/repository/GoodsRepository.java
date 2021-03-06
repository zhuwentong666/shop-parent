package com.zwt.shop.repository;

import com.baidu.shop.document.GoodsDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @ClassName GoodsRepository
 * @Description: TODO
 * @Author zhuwentong
 * @Date 2021/3/5
 * @Version V1.0
 **/
public interface GoodsRepository extends ElasticsearchRepository<GoodsDoc,Long> {
}
