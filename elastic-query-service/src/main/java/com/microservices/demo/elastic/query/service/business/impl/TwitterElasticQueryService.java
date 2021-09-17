package com.microservices.demo.elastic.query.service.business.impl;

import com.microservices.demo.elastic.model.index.impl.TwitterIndexModel;
import com.microservices.demo.elastic.query.service.ElasticQueryClient;
import com.microservices.demo.elastic.query.service.business.ElasticQueryService;
import com.microservices.demo.elastic.query.service.model.ElasticQueryServiceResponseModel;
import com.microservices.demo.elastic.query.service.model.assembler.ElasticQueryServiceResponseModelAssembler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TwitterElasticQueryService implements ElasticQueryService {
    private final ElasticQueryClient<TwitterIndexModel> elasticQueryClient;
    private final ElasticQueryServiceResponseModelAssembler elasticQueryServiceResponseModelAssembler;

    public TwitterElasticQueryService(ElasticQueryClient<TwitterIndexModel> elasticQueryClient, ElasticQueryServiceResponseModelAssembler elasticQueryServiceResponseModelAssembler) {
        this.elasticQueryClient = elasticQueryClient;
        this.elasticQueryServiceResponseModelAssembler = elasticQueryServiceResponseModelAssembler;
    }

    @Override
    public ElasticQueryServiceResponseModel getDocumentById(String id) {
        TwitterIndexModel twitterIndexModel = elasticQueryClient.getIndexModelById(id);
        return elasticQueryServiceResponseModelAssembler.toModel(twitterIndexModel);
    }

    @Override
    public List<ElasticQueryServiceResponseModel> getDocumentsByText(String text) {
        return elasticQueryServiceResponseModelAssembler.toModels(elasticQueryClient.getIndexModelByText(text));
    }

    @Override
    public List<ElasticQueryServiceResponseModel> getAllDocuments() {
        return elasticQueryServiceResponseModelAssembler.toModels(elasticQueryClient.getAllIndexModels());
    }
}
