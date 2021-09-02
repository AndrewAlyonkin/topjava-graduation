package edu.alenkin.topjavagraduation.exception;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public class ExpiredVoteTimeException extends RuntimeException{
    public ExpiredVoteTimeException(String message) {
        super(message);
    }
}
