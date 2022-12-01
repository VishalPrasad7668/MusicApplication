package com.niit.UserTrackService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT,reason = "Track is already Present")
public class TrackAlreadyExistException extends Exception{
}
