package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DistanceMatrixDto {

        private List<String> destinationAddresses;
        private List<String> originAddresses;
        private List<Row> rows;
        private String status;

        @AllArgsConstructor
        @NoArgsConstructor
        @Data
        public static class Row {
            private List<Element> elements;

            @AllArgsConstructor
            @NoArgsConstructor
            @Data
            public static class Element {
                private Distance distance;
                private Duration duration;
                private String status;
            }
        }
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
        public static class Distance {
            private String text;
            private int value;
        }
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
        public static class Duration {
            private String text;
            private int value;

        }
    }
