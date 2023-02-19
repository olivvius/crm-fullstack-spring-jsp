package fr.m2i.spring.Serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import fr.m2i.spring.model.Client;
import fr.m2i.spring.model.Prestation;
import java.io.IOException;

public class PrestationSerializer extends StdSerializer<Prestation> {

    public PrestationSerializer() {
        this(null);
    }

    public PrestationSerializer(Class<Prestation> prestation) {
        super(prestation);
    }

    @Override
    public void serialize(Prestation prestation, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", prestation.getId());
        jsonGenerator.writeStringField("Description", prestation.getDescription());
        jsonGenerator.writeNumberField("durée", prestation.getDuree());
        jsonGenerator.writeNumberField("prix", prestation.getPrix());
        jsonGenerator.writeNumberField("prix total", prestation.getPrixtotal());
        jsonGenerator.writeStringField("Etat", prestation.getEtat());
        jsonGenerator.writeStringField("Client", prestation.getClient().getNom());
        jsonGenerator.writeEndObject();
    }
}
