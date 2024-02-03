package gigachads.noenemies.diploma.storage.jpa.converter;

import com.ctc.wstx.shaded.msv_core.util.Uri;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import static java.util.Collections.emptyList;

@Converter
public class StringURIConverter implements AttributeConverter<URI, String> {

    @Override
    public String convertToDatabaseColumn(URI uri) {
        return uri.toString();
    }

    @Override
    public URI convertToEntityAttribute(String stringUri) {
        try {
            return new URI(stringUri);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}