package com.algorithmicignorance.marketstack.wrapper.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Value
@Jacksonized
@Builder
public class EodResponse {

    private Pagination pagination;
    private List<Data> data;

    @Value
    @Jacksonized
    @Builder
    private static class Pagination {

        private Integer limit;
        private Integer offset;
        private Integer count;
        private Integer total;

    }

    @Value
    @Jacksonized
    @Builder
    private static class Data {

        private Double open;
        private Double high;
        private Double low;
        private Double close;
        private Double volume;
        @JsonProperty("adj_high")
        private Double adjHigh;
        @JsonProperty("adj_low")
        private Double adjLow;
        @JsonProperty("adj_close")
        private Double adjClose;
        @JsonProperty("adj_open")
        private Double adjOpen;
        @JsonProperty("adj_volume")
        private Double adjVolume;
        @JsonProperty("split_factor")
        private Double splitFactor;
        private Double dividend;
        private String symbol;
        private String exchange;
        private String date;

    }

}
