# Parlance

**On-device dictation with NVIDIA Parakeet, for Android, Linux and Windows.** Pick the model
that suits how you talk: stock Parakeet, or a fine-tune of it.

![Parlance on Linux: the Model tab, choosing between stock Parakeet and the fine-tunes](docs/screenshots/desktop-model.png)

**Parlance is the general-purpose app**: every model, for everyday dictation. Its sister
project [**Hebrish**](https://github.com/Hebrish-ASR/Hebrish-App) ([hebrish.app](https://hebrish.app))
is built from the same code but is only for the English-Hebrew code-switching model. The two
are separate apps with separate package names, app IDs, settings and default hotkeys, so they
can be installed side by side (see [Parlance and Hebrish](#parlance-and-hebrish)).

Everything runs **on your device**. The only network traffic is downloading the model you
pick (~630 MB, once) and a periodic check for a newer version.

## Models

Every model is [Parakeet TDT 0.6B v2](https://huggingface.co/nvidia/parakeet-tdt-0.6b-v2),
run with sherpa-onnx (int8).

| Model | Status | For |
| --- | --- | --- |
| **Parakeet (stock)** | available | General English, any speaker. NVIDIA's release, unchanged |
| **English-Hebrew code-switching** | available | English with Hebrew words in it (*teudat zehut*, *arnona*, *bituach leumi*), written in Latin letters. [Model card](https://hebrish.app/models#parakeet); downloaded from the Hebrish CDN |
| **Technical vocabulary** | coming soon | Software, AI and infrastructure terms |
| **Personal (general)** | coming soon | General English in the author's voice (Irish-accented), for slightly higher everyday accuracy |

The fine-tunes are trained on **one speaker's voice**, so they work best for voices like that
one; stock Parakeet is the better choice for anyone else. Models marked *coming soon* appear
in the apps by themselves once published: both apps re-read the catalogue
([`shared/models.json`](shared/models.json)) from this repo when they check for updates.

On held-out recordings, the code-switching model (v1.0, benchmarked 2026-10-19) cut word
error rate on code-switched English from 11.3% to 5.1%, and plain English got better, not worse. Training data, every version's results and the roadmap:
[English-Hebrew-Codeswitching-Parakeet-Finetune](https://github.com/Hebrish-ASR/English-Hebrew-Codeswitching-Parakeet-Finetune).

## Download

| Platform | What it is |
| --- | --- |
| **Android** | A voice keyboard (IME), the companion voice input for HeliBoard. Android 9+, arm64 |
| **Ubuntu** | A tray app for Ubuntu 26.04 amd64 (.deb) |
| **Windows** | A portable tray app (.zip): unzip and run `Parlance.exe` |

Builds are on the [releases page](https://github.com/danielrosehill/Parlance-Dictation/releases):
tags `android-v*` carry the APK, tags `desktop-v*` the .deb and the Windows zip. The Windows
build and the Android app have not yet been tried on real hardware.

## Desktop app (Linux and Windows)

- **Dictate**: press a hotkey anywhere, talk, and the text is pasted where your cursor is.
  The hotkey can be any key or a foot pedal. Tap it to start and stop, or hold it while you
  talk.
- **Transcribe**: record with a level meter, a timer and pause, or open or drop an audio
  file. The transcript fills an editable text box piece by piece; save it as .txt or .md.
- **Model**: choose the model, download it, switch between downloaded ones, keep or delete
  old versions.

Ubuntu: `sudo apt install ./parlance_<version>-1_amd64.deb`, then
`sudo usermod -aG input $USER` for the hotkey, and log in again. It replaces Voice To
Hebrish and Parakeet-Type-Ubuntu, and keeps Voice To Hebrish's settings and downloaded
model. Details: [`desktop/README.md`](desktop/README.md).

## Android app

A voice keyboard (IME) and an optional **floating mic** that sits over any app, so you can
dictate without switching keyboards. Open the app, download a model, and tap **Try it** to test
it in a built-in notepad before setting anything up. HeliBoard's mic key opens the voice
keyboard; the floating mic types straight into the field you are in. Long takes are decoded in
pieces while you talk, so the text is ready soon after you stop (takes up to 20 minutes). Details:
[`android/README.md`](android/README.md).

## Repository layout

| Path | What |
| --- | --- |
| `desktop/` | Linux + Windows app (Python, PySide6, sherpa-onnx). Only the hotkey and paste code differs per OS |
| `android/` | Android voice keyboard and floating mic (Kotlin, sherpa-onnx AAR); the engine is in `android/core/`, shared file for file with Hebrish |
| `shared/models.json` | The model catalogue both apps bundle and re-fetch; a test fails if either app's copy drifts |
| `shared/update-protocol.md` | How models are listed, downloaded and updated, and the quirks both apps work around |

Releases: tag `android-vX.Y.Z` builds the signed APK in CI. Tag `desktop-vX.Y.Z` builds the
Windows zip in CI; the Ubuntu .deb is built with `desktop/build-deb.sh` and added to the same
release. Adding a model needs no release: see `shared/update-protocol.md`.

## Parlance and Hebrish

Both apps come from one codebase and keep the same feature set. What differs is the purpose and
every identifier, so that both can be installed together:

| | Parlance | Hebrish |
| --- | --- | --- |
| Purpose | General dictation, every model | The English-Hebrew code-switching model only |
| Repo | `danielrosehill/Parlance-Dictation` | `Hebrish-ASR/Hebrish-App` |
| Debian package / command | `parlance` | `hebrish` |
| Android app ID | `com.danielrosehill.parlance` | `app.hebrish.dictation` |
| Linux config / data | `~/.config/parlance`, `~/.local/share/parlance` | `~/.config/hebrish`, `~/.local/share/hebrish` |
| Default hotkey (desktop) | `F13` | `F14` |

The desktop hotkey listener reads `/dev/input` without grabbing the device, so if both apps are
bound to the same key, a press fires both. Give each app its own key (Dictate tab).

**Hebrish 0.1.1 and 0.1.2 were released under Parlance's identifiers** (package `parlance`,
app ID `com.danielrosehill.parlance`), because the Hebrish rebrand of 2026-10-19 changed only
the display name. Parlance 0.2.0 installs over them and keeps their settings and models;
Hebrish 0.2.0 and later are a separate install.

## History

Parlance (2026-10-19) is the successor to all of the author's earlier Parakeet dictation
apps, and the general-purpose one ([Hebrish](https://github.com/Hebrish-ASR/Hebrish-App) is its code-switching sibling):

- **Voice To Hebrish** (2026-10-18), the Android + desktop monorepo this repo was copied
  from, history included. It ran only the code-switching model.
- Before that, *English-Hebrew-Codeswitching-Voice-Keyboard-Android* ("Codeswitch Voice")
  and *English-Hebrew-Codeswitching-Dictation-Linux* ("Codeswitch Dictation").
- **Parakeet-Type-Ubuntu** (`parakeet-dictation`), a KDE-only app with stock and
  code-switching profiles.

Later on 2026-10-19 the original Parlance repo was moved to the Hebrish-ASR organisation and
renamed Hebrish-Dictation. This repo was then re-created from it with history kept (from the
Parlance 0.1.0 commit plus two later desktop fixes), so that Parlance stays the general app and
Hebrish the code-switching one.

## License

Apps: MIT. Fine-tuned models: CC-BY-4.0. Stock Parakeet: CC-BY-4.0 (NVIDIA).
