package com.algorithmicignorance.marketstack.wrapper;

import com.algorithmicignorance.marketstack.wrapper.dto.EodResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Wyatt Lang
 *
 *
 */
public class MarketStack {

    private OkHttpClient client;
    private ObjectMapper objectMapper;
    private String apiAccessKey;

    /**
     *
     *  @param apiAccessKey Your marketstack API key that will be appended to the query params for each API call.
     */
    public MarketStack(String apiAccessKey) {
        this.client = new OkHttpClient();
        this.objectMapper = new ObjectMapper();
        this.apiAccessKey = apiAccessKey;
    }

    /**
     *
     */
    public EodResponse getEod(@NonNull List<String> symbols) throws IOException {
        String symbolsAsCsv = this.parseListToCsv(symbols);

        Request eodRequest = new Request.Builder()
                .url("https://api.marketstack.com/v1/eod?access_key=" + this.apiAccessKey + "&symbols=" + symbolsAsCsv)
                .build();

        Response response = client.newCall(eodRequest).execute();
        EodResponse eodResponse = this.objectMapper.readValue(response.body().string(), EodResponse.class);
        return eodResponse;
    }

    /**
     * Helper method that transforms a List<String> representing ticker symbols into a comma-separated list
     * to be used as a query param in API calls.
     */
    private String parseListToCsv(@NonNull List<String> symbols) {
        return symbols.stream()
                .collect(Collectors.joining(","));
    }

}
