package com.example.ojsmobileapp.extras;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
public class Permissions {
    private Context context;

    public Permissions(Context context) {
        this.context = context;
    }

    public ArrayList<String> getPermisosNoAprobados(ArrayList<String> listaPermisos) {
        ArrayList<String> list = new ArrayList<>();
        for (String permiso : listaPermisos) {
            if (ActivityCompat.checkSelfPermission(context, permiso) != PackageManager.PERMISSION_GRANTED) {
                list.add(permiso);
            }
        }
        return list;
    }

    public ArrayList<String> getPermisosAprobados(ArrayList<String> listaPermisos) {
        ArrayList<String> list = new ArrayList<>();
        for (String permiso : listaPermisos) {
            if (ActivityCompat.checkSelfPermission(context, permiso) == PackageManager.PERMISSION_GRANTED) {
                list.add(permiso);
            }
        }
        return list;
    }

    public void getPermission(ArrayList<String> permisosSolicitados) {
        if (!permisosSolicitados.isEmpty()) {
            ActivityCompat.requestPermissions(
                    (Activity) context,
                    permisosSolicitados.toArray(new String[0]),
                    1
            );
        }
    }

    public String onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        StringBuilder s = new StringBuilder();
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    s.append("Permitido: ");
                } else {
                    s.append("Denegado: ");
                }
                s.append(permissions[i]).append("\n");
            }
        }
        return s.toString();
    }
}
