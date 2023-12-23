# Beş Vakit Uygulaması
Android java ile namaz vakit apilerini kullanarak oluşturulmuş namaz vakitlerini gösteren uygulama

<h3>build.gradle Depencines ve packagingOptions bölümü:</h3>

```java

packagingOptions {
  exclude 'META-INF/DEPENDENCIES'
}

dependencies {

    implementation 'androidx.appcompat:appcompat:1.3.1'
    implementation 'com.android.support.constraint:constraint-layout:2.0.4'
    implementation 'com.android.volley:volley:1.2.1'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'com.android.support.test:runner:1.0.2'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.2'
}



```

<h3>AndroidX için gradle.properties içine</h3>

```java
android.useAndroidX=true
android.enableJetifier=true
```
<h3>HTTP Talepleri İçin Kullanılan Kütüphaneler</h3>

```java
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
```

<h3>Tarih ve Zaman Bilgisi için Kullanılan Kütüphaneler</h3>

```java
import java.text.SimpleDateFormat;
import java.util.Calendar;
```
<h3>HTTP Taleplerini Göndermek İçi Sınıf</h3>

```java
// http taleplerini göndermek için sınıf
class HttpRequest extends StringRequest {
    public HttpRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<>();
        headers.put("content-type", "application/json");
        headers.put("authorization", "apikey KEY");

        return headers;
    }
}
```

<h3>HTTP Bağlantısı İçin Get Talebi Oluşturma</h3>

```java
// http bağlantısı için get talebi oluşturma
HttpRequest request = new HttpRequest(Request.Method.GET, "https://api.collectapi.com/pray/all?data.city=corum",
```
<h3>Ekranda Anlık Saat Dakika ve Saniye Biligisini Verme</h3>

```java
// saat dakika ve saniye bilgisi için oluşturulan sınıf
private String getCurrentDateTime() {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", java.util.Locale.getDefault());
        return sdf.format(calendar.getTime());
    }
```
<h3>Uygulama Ekran Görüntüleri</h3>
<table>
  <tr>
    <td>
      <img src="https://github.com/NuhcanATAR/besvakitapp/assets/77950761/89038229-2c27-418d-a870-9bba7a7a0606"/>
    </td>
     <td>
      <img src="https://github.com/NuhcanATAR/besvakitapp/assets/77950761/26929289-a744-4666-b779-18d7f7d40609" width="200" height="400"/>
    </td>
  </tr>
</table>
