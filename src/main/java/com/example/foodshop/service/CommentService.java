package com.example.foodshop.service;

import com.example.foodshop.model.service.CommentServiceModel;
import com.example.foodshop.model.view.CommentViewModel;

import java.util.List;

public interface CommentService {

    CommentViewModel  createComment(CommentServiceModel commentServiceModel);

    List<CommentViewModel> getComments(Long productId);
}
