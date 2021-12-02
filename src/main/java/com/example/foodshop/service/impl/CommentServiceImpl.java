package com.example.foodshop.service.impl;

import com.example.foodshop.model.entity.CommentEntity;
import com.example.foodshop.model.service.CommentServiceModel;
import com.example.foodshop.model.view.CommentViewModel;
import com.example.foodshop.repository.CommentRepository;
import com.example.foodshop.repository.ProductRepository;
import com.example.foodshop.repository.UserRepository;
import com.example.foodshop.service.CommentService;
import com.example.foodshop.service.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public CommentServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, UserRepository userRepository, CommentRepository commentRepository) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentViewModel createComment(CommentServiceModel commentServiceModel) {
        var product = productRepository
                .findById(commentServiceModel.getProductId())
                .orElseThrow(()->new ObjectNotFoundException("Product with id"+commentServiceModel.getProductId()+"not found!"));

        var author = userRepository.findByUsername(commentServiceModel.getCreator())
                .orElseThrow(()->new ObjectNotFoundException("User with id"+commentServiceModel.getProductId()+"not found!"));

        CommentEntity newComment = new CommentEntity();
        newComment.setApproved(false).setTextContent(commentServiceModel.getMessage()).setCreated(LocalDateTime.now())
                .setProduct(product).setAuthor(author);
        commentRepository.save(newComment);

        return mapAsComment(newComment);
    }

    @Override
    public List<CommentViewModel> getComments(Long productId) throws ObjectNotFoundException {

        var productOpt = productRepository.findById(productId);

        if (productOpt.isEmpty()) {
            throw new ObjectNotFoundException("Product with id" + productId + "was not found!");
        }
        return productOpt
                .get()
                .getComments()
                .stream()
                .map(this::mapAsComment)
                .collect(Collectors.toList());
    }

    private CommentViewModel mapAsComment(CommentEntity commentEntity){
        CommentViewModel commentViewModel = new CommentViewModel();

       commentViewModel
               .setCommentId(commentEntity.getId())
               .setCanApprove(true)
               .setCanDelete(true)
               .setCreated(commentEntity.getCreated())
               .setMessage(commentEntity.getTextContent())
               .setUser(commentEntity.getAuthor().getUsername());

        return commentViewModel;
    }
}
