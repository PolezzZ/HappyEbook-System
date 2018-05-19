/**
 * File Name:EsEbookMapper.java
 *
 * Copyright:copyright@2018 WHUT, All Rights Reserved
 *
 * Create Time: 2018年5月19日 上午12:34:22
 */
package com.polezz.ebook.mapper.es;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.polezz.ebook.model.es.EsEbook;

/**
 *
 * @author PolezZ_ (polezz_z@163.com)
 * @version 1.0.0, 2018年5月19日 上午12:34:22
 */
public interface EsEbookMapper
        extends
            ElasticsearchRepository<EsEbook, String> {

    /**
     * 模糊查询(去重)
     * 
     * @param title
     * @param Summary
     * @param content
     * @param tags
     * @param pageable
     * @return
     */
    Page<EsEbook> findDistinctEsEbookByTitleContainingOrSummaryContainingOrContentContainingOrTagsContaining(
            String title, String Summary, String content, String tags,
            Pageable pageable);

    EsEbook findByEbookId(Long ebookId);
}
