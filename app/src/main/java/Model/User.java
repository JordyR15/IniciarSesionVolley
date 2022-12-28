package Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class User {
    private String id;
    private String correo;
    private String clave;
    private String token;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User(JSONObject a) throws JSONException {
        id =  a.getString("id").toString();
        correo =  a.getString("correo").toString();
        clave =  a.getString("clave").toString();
        token =  a.getString("token").toString();

    }

    public static ArrayList<User> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<User> usuario = new ArrayList<>();

        for (int i = 0; i < datos.length(); i++) {
            usuario.add(new User(datos.getJSONObject(i)));
        }
        return usuario;
    }

}
