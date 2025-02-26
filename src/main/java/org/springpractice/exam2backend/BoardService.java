package org.springpractice.exam2backend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springpractice.exam2backend.model.Board;
import org.springpractice.exam2backend.model.Comment;
import org.springpractice.exam2backend.model.request.CreateCommentRequest;
import org.springpractice.exam2backend.model.request.CreatePostRequest;
import org.springpractice.exam2backend.model.response.BoardListItemResponse;
import org.springpractice.exam2backend.model.response.CommentResponse;
import org.springpractice.exam2backend.model.response.PostResponse;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    public void createPost(CreatePostRequest createPostRequest) {
        Board board = Board.builder()
                .title(createPostRequest.getTitle())
                .content(createPostRequest.getContent())
                .writer(createPostRequest.getWriter())
                .build();
        boardRepository.save(board);
    }

    public void createComment(CreateCommentRequest createCommentRequest, Long postIdx) {
        Board post = boardRepository.findById(postIdx).orElse(null);
        if (post == null) {
            throw new RuntimeException("Post not found");
        } else {
            Comment comment = Comment.builder()
                    .content(createCommentRequest.getContent())
                    .writer(createCommentRequest.getWriter())
                    .post(post)
                    .build();
            commentRepository.save(comment);
        }
    }

    public List<BoardListItemResponse> listAll() {
        List<Board> boards = boardRepository.findAll();
        List<BoardListItemResponse> boardListItemResponses = new ArrayList<>();
        for (Board board : boards) {
            BoardListItemResponse result = BoardListItemResponse.builder()
                    .idx(board.getIdx()).title(board.getTitle())
                    .writer(board.getWriter()).commentsNumber(board.getComments().size())
                    .build();
            boardListItemResponses.add(result);
        }
        return boardListItemResponses;
    }

    public PostResponse getPost(Long idx) {
        Board board = boardRepository.findById(idx).orElse(null);
        if (board == null) {
            throw new RuntimeException("Board not found");
        } else {
            List<Comment> comments = board.getComments();
            List<CommentResponse> commentResponses = new ArrayList<>();
            for (Comment comment : comments) {
                CommentResponse commentResponse = CommentResponse.builder()
                        .content(comment.getContent()).writer(comment.getWriter())
                        .build();
                commentResponses.add(commentResponse);
            }
            return PostResponse.builder()
                    .idx(board.getIdx()).title(board.getTitle())
                    .writer(board.getWriter()).content(board.getContent())
                    .comments(commentResponses)
                    .build();
        }
    }
}
