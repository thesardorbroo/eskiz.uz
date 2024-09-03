package uz.sardorbroo.eskizuz.dto.sms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URL;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmsDetailsRootDto {

    @JsonProperty("current_page")
    private Integer currentPage;

    @JsonProperty("path")
    private URL path;

    @JsonProperty("prev_page_url")
    private URL prevPageUrl;

    @JsonProperty("first_page_url")
    private URL firstPageUrl;

    @JsonProperty("last_page_url")
    private URL lastPageUrl;

    @JsonProperty("next_page_url")
    private URL nextPageUrl;

    @JsonProperty("per_page")
    private Integer perPage;

    @JsonProperty("last_page")
    private Integer lastPage;

    @JsonProperty("from")
    private Integer from;

    @JsonProperty("to")
    private Integer to;

    @JsonProperty("total")
    private Integer total;

    @JsonProperty("result")
    private List<SmsDetailsDto> result;

    @JsonProperty("links")
    private List<SmsDetailsLinkDto> links;
}
