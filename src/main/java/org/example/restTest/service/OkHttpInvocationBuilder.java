package org.example.restTest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.exception.BadRequestException;
import com.github.dockerjava.api.exception.ConflictException;
import com.github.dockerjava.api.exception.DockerException;
import com.github.dockerjava.api.exception.InternalServerErrorException;
import com.github.dockerjava.api.exception.NotAcceptableException;
import com.github.dockerjava.api.exception.NotFoundException;
import com.github.dockerjava.api.exception.NotModifiedException;
import com.github.dockerjava.api.exception.UnauthorizedException;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.api.model.StreamType;
import com.github.dockerjava.core.InvocationBuilder;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import okhttp3.Response;
import okhttp3.internal.connection.RealConnection;
import okio.*;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Objects;
import java.util.function.Consumer;

public class OkHttpInvocationBuilder implements InvocationBuilder {
    ObjectMapper objectMapper;

    OkHttpClient okHttpClient;

    Request.Builder requestBuilder;

    public OkHttpInvocationBuilder(ObjectMapper objectMapper, OkHttpClient okHttpClient, HttpUrl httpUrl) {
        this.objectMapper = objectMapper;
        this.okHttpClient = okHttpClient;
        requestBuilder = new Request.Builder()
                .url(httpUrl);
    }
    @Override
    public InvocationBuilder accept(com.github.dockerjava.core.MediaType mediaType) {
        return null;
    }
    @Override
    public InvocationBuilder header(String s, String s1) {
        return null;
    }
    @Override
    public void delete() {
    }
    @Override
    public void get(ResultCallback<Frame> resultCallback) {

    }
    @Override
    public <T> T get(TypeReference<T> typeReference) {
        return null;
    }
    @Override
    public <T> void get(TypeReference<T> typeReference, ResultCallback<T> resultCallback) {
    }

    @Override
    @SneakyThrows
    public InputStream post(Object entity) {
        Request request = requestBuilder
                .post(RequestBody.create(MediaType.parse("application/json"), objectMapper.writeValueAsBytes(entity)))
                .build();
        return execute(request).body().byteStream();
    }

    protected Response execute(Request request) {
        return execute(okHttpClient, request);
    }
    @SneakyThrows(IOException.class)
    protected Response execute(OkHttpClient okHttpClient, Request request) {
        Response response = okHttpClient.newCall(request).execute();
        if (!response.isSuccessful()) {
            String body = response.body().string();
            switch (response.code()) {
                case 304:
                    throw new NotModifiedException(body);
                case 400:
                    throw new BadRequestException(body);
                case 401:
                    throw new UnauthorizedException(body);
                case 404:
                    throw new NotFoundException(body);
                case 406:
                    throw new NotAcceptableException(body);
                case 409:
                    throw new ConflictException(body);
                case 500:
                    throw new InternalServerErrorException(body);
                default:
                    throw new DockerException(body, response.code());
            }
        } else {
            return response;
        }
    }


    @Override
    public void post(Object o, InputStream inputStream, ResultCallback<Frame> resultCallback) {

    }

    @Override
    public <T> T post(Object o, TypeReference<T> typeReference) {
        return null;
    }

    @Override
    @SneakyThrows(JsonProcessingException.class)
    public <t> void post(Object entity, TypeReference<t> typeReference, ResultCallback<t> resultCallback) {
        post(typeReference, resultCallback, new ByteArrayInputStream(objectMapper.writeValueAsBytes(entity)));
    }

    @Override
    public <T> T post(TypeReference<T> typeReference, InputStream inputStream) {
        return null;
    }

    @Override
    public <T> void post(TypeReference<T> typeReference, ResultCallback<T> resultCallback, InputStream inputStream) {
    }
    @Override
    public void postStream(InputStream inputStream) {
    }
    @Override
    public InputStream get() {
        return null;
    }

    @Override
    public void put(InputStream inputStream, com.github.dockerjava.core.MediaType mediaType) {

    }
}
