package fr.m2i.spring.Serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import fr.m2i.spring.model.Client;
import fr.m2i.spring.model.Prestation;
import java.io.IOException;

public class ClientSerializer extends StdSerializer<Client> {

    public ClientSerializer() {
        this(null);
    }

    public ClientSerializer(Class<Client> client) {
        super(client);
    }

    @Override
    public void serialize(Client client, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", client.getId());
        jsonGenerator.writeStringField("Compagnie", client.getNom());
        jsonGenerator.writeStringField("nom", client.getStatut());

        jsonGenerator.writeArrayFieldStart("prestations");
        for (Prestation p : client.getPrestation()) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", p.getId());
            jsonGenerator.writeStringField("description", p.getDescription());
            jsonGenerator.writeStringField("etat", p.getEtat());
            jsonGenerator.writeNumberField("prix unitaire", p.getPrix());
            jsonGenerator.writeNumberField("prix total", p.getPrixtotal());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}
