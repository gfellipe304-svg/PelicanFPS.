package com.pelican.fps;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.provider.Settings;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {
    private final int bg = Color.rgb(16,24,32);
    private final int panel = Color.rgb(28,40,51);
    private final int green = Color.rgb(22,199,132);
    private LinearLayout root;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(bg);
        getWindow().setNavigationBarColor(bg);
        buildUI();
    }

    private void buildUI() {
        ScrollView scroll = new ScrollView(this);
        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(20), dp(22), dp(20), dp(28));
        root.setBackgroundColor(bg);
        scroll.setFillViewport(true);
        scroll.addView(root);
        setContentView(scroll);

        TextView brand = text("PELICAN FPS", 27, Color.WHITE, true);
        root.addView(brand);
        TextView subtitle = text("ANDROID GAME PERFORMANCE PANEL", 11, green, true);
        subtitle.setPadding(0, dp(4), 0, dp(18));
        root.addView(subtitle);

        LinearLayout hero = card();
        TextView title = text("ROBLOX • PERFORMANCE", 17, Color.WHITE, true);
        hero.addView(title);
        TextView disclaimer = text(
            "Otimização segura: este app não injeta código, não altera o Roblox e não promete FPS que o aparelho/jogo não suporta.",
            14, Color.rgb(205,216,224), false);
        disclaimer.setPadding(0, dp(10), 0, dp(4));
        hero.addView(disclaimer);
        root.addView(hero);

        addAction("MODO DE DESEMPENHO", "Abrir as configurações de bateria para procurar o modo desempenho.", "BATERIA", v -> openSettings(Settings.ACTION_BATTERY_SAVER_SETTINGS));
        addAction("TAXA DE ATUALIZAÇÃO", "Ajuste a tela para a maior taxa disponível (por exemplo, 90/120 Hz). Isso não força o Roblox a renderizar nessa taxa.", "CONFIGURAR TELA", v -> openSettings(Settings.ACTION_DISPLAY_SETTINGS));
        addAction("GRÁFICOS DO ROBLOX", "Dentro do Roblox: Menu → Configurações → Modo gráfico Manual → reduza a Qualidade gráfica se houver travamentos.", "VER DICA", v -> showInfo("No Roblox, abra o menu da experiência e entre em Configurações. Coloque Modo gráfico em Manual e diminua a qualidade até o jogo ficar estável."));
        addAction("REDUZIR AQUECIMENTO", "Feche apps em segundo plano, evite jogar carregando e faça uma pausa se o celular esquentar muito.", "VER DICA", v -> showInfo("Calor pode fazer o Android reduzir o desempenho. Feche apps que não usa, tire a capa se estiver retendo calor e deixe o aparelho esfriar."));
        addAction("REDE E PING", "FPS e ping são diferentes. Se os jogadores teleportam ou demoram a responder, teste uma conexão Wi‑Fi estável.", "VER DICA", v -> showInfo("Se a imagem trava, pode ser FPS. Se tudo responde atrasado, pode ser rede/ping. Teste Wi‑Fi estável e aproxime-se do roteador."));

        TextView foot = text("SEM ROOT • SEM INJEÇÃO • SEM GARANTIA FALSA DE 144 FPS", 10, Color.rgb(148,163,175), true);
        foot.setGravity(Gravity.CENTER);
        foot.setPadding(0, dp(20), 0, 0);
        root.addView(foot);
    }

    private void addAction(String heading, String body, String button, View.OnClickListener click) {
        LinearLayout c = card();
        TextView h = text(heading, 15, green, true);
        c.addView(h);
        TextView b = text(body, 13, Color.rgb(205,216,224), false);
        b.setPadding(0, dp(8), 0, dp(12));
        c.addView(b);
        Button btn = new Button(this);
        btn.setText(button);
        btn.setAllCaps(false);
        btn.setTextColor(Color.WHITE);
        btn.setBackgroundTintList(android.content.res.ColorStateList.valueOf(Color.rgb(40,90,77)));
        btn.setOnClickListener(click);
        c.addView(btn);
        root.addView(c);
    }

    private LinearLayout card() {
        LinearLayout c = new LinearLayout(this);
        c.setOrientation(LinearLayout.VERTICAL);
        c.setPadding(dp(16), dp(15), dp(16), dp(12));
        c.setBackgroundColor(panel);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, -2);
        lp.bottomMargin = dp(12);
        c.setLayoutParams(lp);
        return c;
    }

    private TextView text(String s, int size, int color, boolean bold) {
        TextView t = new TextView(this);
        t.setText(s);
        t.setTextSize(size);
        t.setTextColor(color);
        if (bold) t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        return t;
    }

    private void openSettings(String action) {
        try { startActivity(new Intent(action)); }
        catch (Exception e) { showInfo("Essa tela de configurações não está disponível neste aparelho. Abra as Configurações do Android manualmente."); }
    }

    private void showInfo(String msg) {
        new android.app.AlertDialog.Builder(this)
            .setTitle("Pelican FPS")
            .setMessage(msg)
            .setPositiveButton("OK", null)
            .show();
    }

    private int dp(int v) {
        return (int)(v * getResources().getDisplayMetrics().density + 0.5f);
    }
}
