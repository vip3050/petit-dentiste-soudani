# 📱 Super Dentiste — Projet Android Studio

Application Android officielle du jeu **Super Dentiste** (Cabinet Soudani).

Le jeu est un fichier HTML5 autonome (`app/src/main/assets/index.html`) affiché en plein écran
dans une WebView native optimisée. Résultat : une application légère (~1 Mo), rapide, qui
fonctionne intégralement hors ligne.

---

## Ouvrir le projet

1. Android Studio → **Open**
2. Sélectionnez **ce dossier** (`android/`, celui qui contient `settings.gradle`)
3. Laissez Gradle se synchroniser (2 à 5 min la première fois)
4. ▶️ **Run** sur un téléphone ou un émulateur

> ℹ️ Le `gradle-wrapper.jar` n'est pas versionné. Android Studio le régénère automatiquement
> à la première ouverture. Pour l'obtenir en ligne de commande : `gradle wrapper` (si Gradle
> est installé), ou lancez simplement le projet une fois depuis Android Studio.

---

## Caractéristiques techniques

| | |
|---|---|
| Application ID | `com.cabinetsoudani.superdentiste` |
| minSdk | 23 (Android 6.0 — couvre ~99 % des appareils) |
| targetSdk / compileSdk | 34 (Android 14) |
| Langue Java | 17 |
| Orientation | Portrait verrouillé |
| Permissions | `INTERNET` (liens de rendez-vous uniquement), `VIBRATE` |
| Taille approximative | ~1 Mo |
| Dépendances | AppCompat, AndroidX WebKit, Core SplashScreen |

### Ce que fait `MainActivity`
- Splash screen natif aux couleurs du cabinet
- WebView plein écran, zoom désactivé, JavaScript et DOM storage activés (sauvegarde de la progression)
- Les liens externes (rendez-vous, téléphone) s'ouvrent **hors** de l'application
- Bouton retour Android géré proprement
- L'écran reste allumé pendant une partie

---

## Compiler pour le Play Store

```bash
./gradlew bundleRelease      # Windows : gradlew.bat bundleRelease
```
→ `app/build/outputs/bundle/release/app-release.aab`

La signature de release lit un fichier **`keystore.properties`** placé à la racine de `android/` :

```properties
storeFile=/chemin/absolu/super-dentiste.jks
storePassword=…
keyAlias=superdentiste
keyPassword=…
```

Ce fichier est ignoré par Git (`.gitignore`) — il ne doit jamais être publié.
Sans lui, le build de release utilise la signature debug (pour test local uniquement).

---

## Mettre à jour le jeu

1. Remplacez `app/src/main/assets/index.html` par la nouvelle version du jeu
2. Dans `app/build.gradle` : `versionCode` +1 et `versionName` mis à jour
3. Regénérez le `.aab` **avec la même clé de signature**

---

📖 Procédure complète de publication : voir `../playstore/GUIDE-PUBLICATION.md`
