# ⚽ Maç Analiz PRO – APK oluşturma paketi

Bu sürümdeki temel sorun düzeltildi: `index.html` dosyasını Chrome'da açmak yerine uygulamanın Android `WebView + Java` köprüsü kullanılmalıdır. Tarayıcıda açılırsa artık `Failed to fetch` yerine APK'nın kurulması gerektiğini açıkça bildirir.

## APK'yı telefonda oluşturma

1. GitHub'da yeni bir repo oluşturun. Örn: `MacAnalizPRO`.
2. Bu ZIP'in içindeki **dosyaları** repo içine yükleyin.
3. GitHub'da **Actions** sekmesine girin.
4. `Maç Analiz PRO - APK Build` iş akışını seçin.
5. **Run workflow** ile çalıştırın.
6. İşlem bitince workflow sayfasındaki **Artifacts** bölümünden `MacAnalizPRO-debug-APK` dosyasını indirin.
7. ZIP'i açın ve `app-debug.apk` dosyasını telefona kurun.

GitHub Actions, derleme çıktısını artifact olarak saklayıp indirmenize izin verir.

## API-Football

Uygulama API-Football v3 kullanır. API anahtarınızı uygulama içindeki ayarlardan girin. Anahtarı herkese açık GitHub deposuna yazmayın.

Örnek ligler:
- İngiltere Premier League: 39
- İtalya Serie A: 135
- İspanya La Liga: 140
- Almanya Bundesliga: 78
- Fransa Ligue 1: 61
- Türkiye Süper Lig: 203

## Önemli

Bu paket burada APK olarak derlenmiş değildir; GitHub Actions'ın Android ortamında otomatik derlemesi için hazırlanmıştır. Bu yöntemle gerçek `app-debug.apk` oluşturulur.
