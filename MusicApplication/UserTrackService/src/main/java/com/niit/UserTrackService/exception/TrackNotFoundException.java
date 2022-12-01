package com.niit.UserTrackService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Track is not availble!!")
public class TrackNotFoundException extends Exception{
}
