/**
 * File Name:EsEbookService.java
 *
 * Copyright:copyright@2018 WHUT, All Rights Reserved
 *
 * Create Time: 2018年5月19日 上午12:31:45
 */
package com.polezz.ebook.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.elasticsearch.search.aggregations.AggregationBuilders.terms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.search.SearchParseException;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ResultsExtractor;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.polezz.ebook.mapper.es.EsEbookMapper;
import com.polezz.ebook.model.User;
import com.polezz.ebook.model.es.EsEbook;
import com.polezz.ebook.service.EsEbookService;
import com.polezz.ebook.service.UserService;
import com.polezz.ebook.vo.TagVO;


/**
 *
 * @author PolezZ_ (polezz_z@163.com)
 * @version 1.0.0, 2018年5月19日 上午12:31:45
 */
@Service
public class EsEbookServiceImpl implements EsEbookService {
    @Autowired
    private EsEbookMapper esEbookMapper;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private UserService userService;
    
    private static final Pageable TOP_5_PAGEABLE = new PageRequest(0, 5);
    private static final String EMPTY_KEYWORD = "";
    @Override
    public void removeEsEbook(String id) {
        esEbookMapper.delete(id);
    }

    @Override
    public EsEbook updateEsEbook(EsEbook esEbook) {
        return esEbookMapper.save(esEbook);
    }

    @Override
    public EsEbook getEsEbookByEbookId(Long ebookId) {
        return esEbookMapper.findByEbookId(ebookId);
    }

    @Override
    public Page<EsEbook> listNewestEsEbooks(String keyword, Pageable pageable) throws SearchParseException {
        Page<EsEbook> pages = null;
        Sort sort = new Sort(Direction.DESC,"createTime"); 
        if (pageable.getSort() == null) {
            pageable = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), sort);
        }
 
        pages = esEbookMapper.findDistinctEsEbookByTitleContainingOrSummaryContainingOrContentContainingOrTagsContaining(keyword,keyword,keyword,keyword, pageable);
 
        return pages;
    }

    @Override
    public Page<EsEbook> listHotestEsEbooks(String keyword, Pageable pageable) throws SearchParseException{
 
        Sort sort = new Sort(Direction.DESC,"readSize","commentSize","likeSize","createTime"); 
        if (pageable.getSort() == null) {
            pageable = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), sort);
        }
 
        return esEbookMapper.findDistinctEsEbookByTitleContainingOrSummaryContainingOrContentContainingOrTagsContaining(keyword, keyword, keyword, keyword, pageable);
    }

    @Override
    public Page<EsEbook> listEsEbooks(Pageable pageable) {
        return esEbookMapper.findAll(pageable);
    }
 
 
    /**
     * 最新前5
     * @param keyword
     * @return
     */
    @Override
    public List<EsEbook> listTop5NewestEsEbooks() {
        Page<EsEbook> page = this.listHotestEsEbooks(EMPTY_KEYWORD, TOP_5_PAGEABLE);
        return page.getContent();
    }
    
    /**
     * 最热前5
     * @param keyword
     * @return
     */
    @Override
    public List<EsEbook> listTop5HotestEsEbooks() {
        Page<EsEbook> page = this.listHotestEsEbooks(EMPTY_KEYWORD, TOP_5_PAGEABLE);
        return page.getContent();
    }

    @Override
    public List<TagVO> listTop30Tags() {
 
        List<TagVO> list = new ArrayList<>();
        // given
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())
                .withSearchType(SearchType.QUERY_THEN_FETCH)
                .withIndices("ebook").withTypes("ebook")
                .addAggregation(terms("tags").field("tags").order(Terms.Order.count(false)).size(30))
                .build();
        // when
        Aggregations aggregations = elasticsearchTemplate.query(searchQuery, new ResultsExtractor<Aggregations>() {
            @Override
            public Aggregations extract(SearchResponse response) {
                return response.getAggregations();
            }
        });
        
        StringTerms modelTerms =  (StringTerms)aggregations.asMap().get("tags"); 
            
        Iterator<Bucket> modelBucketIt = modelTerms.getBuckets().iterator();
        while (modelBucketIt.hasNext()) {
            Bucket actiontypeBucket = modelBucketIt.next();
 
            list.add(new TagVO(actiontypeBucket.getKey().toString(),
                    actiontypeBucket.getDocCount()));
        }
        return list;
    }
    
    @Override
    public List<User> listTop12Users() {
 
        List<String> usernamelist = new ArrayList<>();
        // given
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())
                .withSearchType(SearchType.QUERY_THEN_FETCH)
                .withIndices("ebook").withTypes("ebook")
                .addAggregation(terms("users").field("username").order(Terms.Order.count(false)).size(12))
                .build();
        // when
        Aggregations aggregations = elasticsearchTemplate.query(searchQuery, new ResultsExtractor<Aggregations>() {
            @Override
            public Aggregations extract(SearchResponse response) {
                return response.getAggregations();
            }
        });
        
        StringTerms modelTerms =  (StringTerms)aggregations.asMap().get("users"); 
        modelTerms.getBuckets().iterator();
        Iterator<Bucket> modelBucketIt = modelTerms.getBuckets().iterator();
        while (modelBucketIt.hasNext()) {
            Bucket actiontypeBucket = modelBucketIt.next();
            String username = actiontypeBucket.getKey().toString();
            usernamelist.add(username);
        }
        List<User> list = userService.listUsersByUsernames(usernamelist);
        return list;
    }
}