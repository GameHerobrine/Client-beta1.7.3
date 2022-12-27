// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

package net.minecraft.src;

import net.minecraft.client.Minecraft;

import java.net.HttpURLConnection;
import java.net.URL;

// Referenced classes of package net.minecraft.src:
//            Session

public class ThreadCheckHasPaid extends Thread {

  final Minecraft field_28146_a; /* synthetic field */

  public ThreadCheckHasPaid(Minecraft minecraft) {
    //        super();
    field_28146_a = minecraft;
  }

  public void run() {
    try {
      HttpURLConnection httpurlconnection =
          (HttpURLConnection)
              (new URL(
                  "https://login.minecraft.net/session?name=" +
                      field_28146_a.session.username +
                      "&session=" +
                      field_28146_a.session.sessionId))
                  .openConnection();
      httpurlconnection.connect();
      if (httpurlconnection.getResponseCode() == 400) {
        Minecraft.hasPaidCheckTime = System.currentTimeMillis();
      }
      httpurlconnection.disconnect();
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }
}
