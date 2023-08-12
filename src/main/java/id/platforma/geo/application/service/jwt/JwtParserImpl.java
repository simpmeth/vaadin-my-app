package id.platforma.geo.application.service.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;


@Service
@RequiredArgsConstructor
public class JwtParserImpl implements JwtParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtParserImpl.class);
    private static final String BEARER_PREFIX = "Bearer";
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    private final ObjectMapper objectMapper;

    @Override
    public JwtTokenPayload getPayload(String token) {
        return disassembledToken(token);
    }

    private JwtTokenPayload disassembledToken(String token) {
        String jwtToken = (token.contains(BEARER_PREFIX))
                ? token.substring("Bearer".length() + 1)
                : token;
        String[] tokenParts = jwtToken.split("\\.");

        if (tokenParts.length < 3) {
            LOGGER.warn("jwt token is may not valid");
        }

        String base64EncodedHeader = tokenParts[0];
        String base64EncodedBody = tokenParts[1];
        Base64 base64Url = new Base64(true);
        String header = new String(base64Url.decode(base64EncodedHeader), DEFAULT_CHARSET);

        try {
            ObjectNode headerJwt = (ObjectNode) this.objectMapper.readTree(header);
            if (headerJwt.get("alg").asText().matches("RS256") &&
                    headerJwt.get("typ").asText().matches("JWT")) {
                String body = new String(base64Url.decode(base64EncodedBody), DEFAULT_CHARSET);
                return objectMapper.readValue(body, JwtTokenPayload.class);
            } else {
                LOGGER.warn("unknown encoded algorithm");
            }
        } catch (JsonProcessingException e) {
            LOGGER.error("json processing exception", e);
        }

        return null;
    }
}
