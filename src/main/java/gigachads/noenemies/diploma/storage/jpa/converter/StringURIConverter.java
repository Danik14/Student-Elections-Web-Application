package gigachads.noenemies.diploma.storage.jpa.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.net.URI;
import java.net.URISyntaxException;

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