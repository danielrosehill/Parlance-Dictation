**Parlance 0.4.0 for Android: level with Hebrish 0.4.0.**

- **Floating mic** (optional): a mic over every app, so you can dictate without switching keyboards. It is an accessibility service; the app walks you through turning it on. Drag it anywhere. Pause, Retake, Discard, and Insert when no field had focus. Brown, so it is easy to tell apart from Hebrish's.
- **Long takes**: speech is cut at pauses and decoded while you talk, so only the last few seconds are left after Stop. The 90 s cap is now 20 minutes. *Type as you go* inserts each piece as it is ready.
- **Faster to text**: silence is trimmed before decoding, and the model is warmed up when it loads.
- **New screens**: a home screen, plus Models, Voice keyboard, Floating mic, Dictation, and Help and links. Download any model, or all of them, one after another.
- **Code-switching model fixed**: it now downloads from the Hebrish CDN (v1.0). 0.2.0 pointed at a Hugging Face repo that is now gated, so that download failed.
- Settings and the stock model carry over from 0.2.0. arm64-v8a only. Android 9+. Not yet run on a device.
