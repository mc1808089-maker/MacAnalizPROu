# Maç Analiz PRO ULTRA — Native API 4.1

Önceki sürümde Android WebView içinden JavaScript `fetch` ile API çağrısı yapılıyordu. Bu sürümde API çağrısı Android'in native HTTPS katmanına taşındı.

## Neden?
WebView'da görülen `Failed to fetch` hatasını ortadan kaldırmak için API isteği Java `HttpURLConnection` ile yapılıyor. API anahtarı `x-apisports-key` header'ında gönderiliyor.

## Kullanım
1. APK'yı Android Studio ile derleyin.
2. Uygulamayı açın.
3. API-Football anahtarınızı girin.
4. Lig ID ve sezonu girin.
5. Bugünün maçlarını getir'e basın.

Örnek:
- Premier League: 39
- Serie A: 135
- La Liga: 140
- Bundesliga: 78
- Ligue 1: 61
- Türkiye Süper Lig: 203

## Önemli
API anahtarını paylaşmayın. Üretim uygulamasında anahtarın cihaz istemcisinde tutulması yerine güvenli bir backend/proxy kullanılması önerilir. API-Football da anahtarın korunmasına ve proxy/cache yaklaşımına dikkat çekiyor.

Bu ortamda Android SDK/Gradle olmadığı için gerçek APK burada derlenemiyor.
