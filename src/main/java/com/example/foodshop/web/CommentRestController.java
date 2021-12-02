package com.example.foodshop.web;

import com.example.foodshop.model.binding.CommentBindingModel;
import com.example.foodshop.model.service.CommentServiceModel;
import com.example.foodshop.model.validation.ApiError;
import com.example.foodshop.model.view.CommentViewModel;
import com.example.foodshop.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
public class CommentRestController {
    private final CommentService commentService;
    private final ModelMapper modelMapper;

    public CommentRestController(CommentService commentService, ModelMapper modelMapper) {
        this.commentService = commentService;
        this.modelMapper = modelMapper;
    }
    @Transactional
    @GetMapping("/api/{productId}/comments")
    public ResponseEntity<List<CommentViewModel>> getComments(@PathVariable Long productId, Principal principal) {

        return ResponseEntity.ok(commentService.getComments(productId));
    }

    @PostMapping("/api/{productId}/comments")
    public ResponseEntity<CommentViewModel> newComment(
            @AuthenticationPrincipal UserDetails principal,
            @PathVariable Long productId,
           @RequestBody @Valid CommentBindingModel commentBindingModel){

        CommentServiceModel serviceModel = modelMapper.map(commentBindingModel, CommentServiceModel.class);
        serviceModel.setCreator(principal.getUsername());
        serviceModel.setProductId(productId);

        CommentViewModel newComment = commentService.createComment(serviceModel);

        URI locationOfNewComment =
                URI.create(String.format("/api/%s/comments/%s", productId, newComment.getCommentId()));

      return ResponseEntity.created(locationOfNewComment).body(newComment);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> onValidationFailure(MethodArgumentNotValidException exception){
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        exception.getFieldErrors().forEach(fieldError -> {
            apiError.addFieldWithError(fieldError.getField());
        });
        return ResponseEntity.badRequest().body(apiError);
    }
}
