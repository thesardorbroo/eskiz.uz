package uz.sardorbroo.eskizuz.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class JwtUtils {

    private static final Base64.Decoder JWT_DECODER = Base64.getUrlDecoder();
    private static final Integer VALID_TOKEN_CHUNKS_LENGTH = 3;
    private static final Integer BODY_OF_TOKEN_INDEX = 1; // it should be 2 but 2 is equal to 1 in list(array)
    private static final String TOKEN_SPLITERATOR = "\\.";

    public static String getDecodedTokenBody(String token) {
        log.trace("Get decoded body from token. Token: {}", token);

        List<String> chunks = getDecodedTokenByChunks(token);
        String tokenBodyChunk = chunks.get(BODY_OF_TOKEN_INDEX);

        log.trace("Decoded body has extracted successfully from token");
        return tokenBodyChunk;
    }

    public static List<String> getDecodedTokenByChunks(String token) {
        log.trace("Start decode token and return as chunks. Token: {}", token);

        List<String> chunks = splitToken(token);
        if (!Objects.equals(VALID_TOKEN_CHUNKS_LENGTH, chunks.size())) {
            log.warn("Token has not split expected! Chunks: {}", chunks);
            return Collections.emptyList();
        }

        List<String> decodedChunks = chunks.stream()
                .map(JWT_DECODER::decode)
                .map(String::new)
                .collect(Collectors.toList());

        log.trace("Token has chunked and decoded successfully");
        return decodedChunks;
    }

    private static List<String> splitToken(String token) {
        log.trace("Split token: {}", token);

        if (StringUtils.isBlank(token)) {
            log.warn("Token is blank! Cannot decode the JWT token!");
            return Collections.emptyList();
        }

        log.trace("Token has split successfully");
        return Arrays.asList(token.split(TOKEN_SPLITERATOR));
    }
}
