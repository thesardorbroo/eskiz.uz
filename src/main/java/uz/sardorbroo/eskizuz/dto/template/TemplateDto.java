package uz.sardorbroo.eskizuz.dto.template;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import uz.sardorbroo.eskizuz.utils.TemplateUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TemplateDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("template")
    private String template;

    @JsonProperty("original_text")
    private String originalText;

    @JsonProperty("status")
    private TemplateStatus status;

    public void setStatus(String status) {
        this.status = TemplateUtils.findByStatus(status)
                .orElseThrow(() -> new IllegalArgumentException("Template status has not found with " + status));
    }
}
