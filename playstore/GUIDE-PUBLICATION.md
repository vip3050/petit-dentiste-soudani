# 📱 Guide complet — Build Android Studio & publication Play Store
### Application « Super Dentiste » — Cabinet Soudani

---

## PARTIE 1 — Ouvrir et compiler le projet dans Android Studio

### 1.1 Prérequis
- **Android Studio** (version Koala 2024.1 ou plus récente) → https://developer.android.com/studio
- **JDK 17** (inclus dans Android Studio, rien à installer séparément)
- Environ 3 Go d'espace disque pour le SDK

### 1.2 Récupérer le projet
Téléchargez le dossier `android/` du dépôt :
```
https://github.com/vip3050/petit-dentiste-soudani
```
(Bouton vert **Code → Download ZIP**, puis décompressez.)

### 1.3 Ouvrir le projet
1. Lancez Android Studio → **Open**
2. Sélectionnez le dossier **`android`** (celui qui contient `settings.gradle`) — surtout pas le dossier parent
3. Android Studio télécharge Gradle et les dépendances automatiquement (2 à 5 min la première fois)
4. Si une bannière propose *"Install missing SDK"* ou *"Upgrade Gradle plugin"* → cliquez **Install / Accept**

### 1.4 Tester sur un téléphone
**Option A — vrai téléphone (recommandé)**
1. Sur le téléphone : Paramètres → À propos → appuyez 7 fois sur « Numéro de build » pour activer le mode développeur
2. Paramètres → Options pour les développeurs → activez **Débogage USB**
3. Branchez le téléphone en USB, acceptez la demande d'autorisation
4. Dans Android Studio, sélectionnez l'appareil en haut puis cliquez sur ▶️ **Run**

**Option B — émulateur**
Tools → Device Manager → Create Device → Pixel 6 → Android 14 → Finish, puis ▶️ Run.

---

## PARTIE 2 — Créer la clé de signature (à faire UNE SEULE FOIS)

⚠️ **Cette clé est irremplaçable.** Si vous la perdez, vous ne pourrez plus jamais mettre à jour l'application sur le Play Store. Sauvegardez-la dans au moins deux endroits sûrs (clé USB + cloud privé).

1. Dans Android Studio : **Build → Generate Signed App Bundle / APK**
2. Choisissez **Android App Bundle** → **Next**
3. Sous « Key store path », cliquez **Create new…**
4. Remplissez :
   - **Key store path** : `C:\cles\super-dentiste.jks` (ou tout autre chemin sûr)
   - **Password** : un mot de passe fort (notez-le !)
   - **Alias** : `superdentiste`
   - **Validity (years)** : `30`
   - **First and Last Name** : Cabinet Soudani
   - **Organization** : Cabinet Soudani
   - **Country Code** : `DZ`
5. **OK**

### 2.1 Enregistrer la clé pour les builds automatiques
Créez un fichier **`keystore.properties`** à la racine du dossier `android/` :
```properties
storeFile=C:/cles/super-dentiste.jks
storePassword=VOTRE_MOT_DE_PASSE
keyAlias=superdentiste
keyPassword=VOTRE_MOT_DE_PASSE
```
Ce fichier est déjà dans le `.gitignore` : il ne sera jamais publié sur GitHub. ✅

---

## PARTIE 3 — Générer le fichier à envoyer au Play Store (.aab)

### Depuis Android Studio
**Build → Generate Signed App Bundle / APK → Android App Bundle → Next**
→ sélectionnez votre keystore → **release** → **Create**

### Ou en ligne de commande
```bash
cd android
./gradlew bundleRelease          # Windows : gradlew.bat bundleRelease
```

📦 Le fichier se trouve dans :
```
android/app/build/outputs/bundle/release/app-release.aab
```
C'est **ce fichier .aab** que vous téléverserez sur le Play Store.

> Pour tester l'application signée sur un téléphone avant publication :
> `./gradlew assembleRelease` → `app/build/outputs/apk/release/app-release.apk`

---

## PARTIE 4 — Créer le compte développeur Google Play

1. Rendez-vous sur https://play.google.com/console
2. Connectez-vous avec le compte Google du cabinet
3. Payez les **frais uniques de 25 $ US** (carte bancaire internationale)
4. Choisissez le type de compte :
   - **Organisation** (recommandé pour le cabinet) → une vérification D-U-N-S peut être demandée, comptez quelques jours
   - **Personnel** → validation plus rapide, mais **obligation de tests fermés : 12 testeurs pendant 14 jours consécutifs** avant de pouvoir publier
5. Complétez la vérification d'identité (pièce d'identité + adresse)

---

## PARTIE 5 — Créer la fiche de l'application

**Play Console → Créer une application**
- Nom : `Super Dentiste`
- Langue par défaut : Français (France)
- Type : **Jeu**
- Gratuite : **Oui** (⚠️ irréversible — une app gratuite ne peut jamais devenir payante)

### 5.1 Fiche Play Store (Croissance → Présence sur le Store → Fiche principale)
Copiez-collez depuis `playstore/textes/fiche-play-store-FR.md`, puis ajoutez les traductions
arabe (`-AR.md`) et anglaise (`-EN.md`) via **Gérer les traductions**.

| Élément | Fichier à téléverser | Format requis |
|---|---|---|
| Icône | `playstore/icons/play-store-icon-512.png` | 512×512 PNG |
| Image de mise en avant | `playstore/graphics/feature-graphic-1024x500.png` | 1024×500 PNG/JPG |
| Captures téléphone | `playstore/screenshots/*.jpg` (2 à 8) | 1080×1920 |

### 5.2 Questionnaires obligatoires (Règles → Contenu de l'application)

**Politique de confidentialité**
```
https://vip3050.github.io/petit-dentiste-soudani/privacy.html
```

**Accès à l'application** → « Toutes les fonctionnalités sont disponibles sans restriction »

**Annonces** → **Non**, l'application ne contient pas d'annonces

**Classification du contenu** → questionnaire → catégorie **Jeu**
→ répondez **Non** à toutes les questions (violence, sexualité, langage grossier, drogues,
jeux d'argent, achats, partage de position, contenu généré par les utilisateurs)
→ résultat attendu : **PEGI 3 / Tout public**

**Public cible** → cochez toutes les tranches d'âge, y compris **5 ans et moins**
→ l'application entre dans le **programme Familles** : vérifiez bien qu'aucune publicité
ni analyse tierce ne sera jamais ajoutée par la suite.

**Sécurité des données** → voir `playstore/textes/securite-des-donnees.md`
(réponse courte : **aucune donnée collectée, aucune donnée partagée**)

**Application gouvernementale** → Non
**Fonctionnalités financières** → Aucune
**Santé** → Non (jeu de divertissement, pas une application médicale)

---

## PARTIE 6 — Publier

1. **Tests → Test interne** : créez une version, téléversez le `.aab`, ajoutez votre e-mail comme testeur.
   Installez et vérifiez le jeu sur un vrai téléphone.
2. *(Compte personnel uniquement)* **Tests fermés** avec 12 testeurs pendant 14 jours consécutifs.
3. **Production → Créer une version** :
   - Téléversez `app-release.aab`
   - Nom de la version : `1.0.0`
   - Notes de version :
     ```
     <fr-FR>
     🦷 Première version de Super Dentiste !
     16 mini-jeux, 40 niveaux, 2 dentistes jouables, français et arabe.
     Sans publicité, sans achat intégré, fonctionne hors ligne.
     </fr-FR>
     ```
4. Sélectionnez les pays de diffusion (Algérie + monde entier recommandé)
5. **Envoyer pour examen** → délai habituel : **1 à 7 jours**

---

## PARTIE 7 — Mettre à jour l'application plus tard

1. Modifiez le jeu → remplacez `android/app/src/main/assets/index.html`
2. Dans `app/build.gradle`, incrémentez :
   ```gradle
   versionCode 2          // +1 à chaque envoi, obligatoire
   versionName "1.1.0"
   ```
3. Regénérez le `.aab` avec **la même clé de signature**
4. Play Console → Production → Créer une version → téléverser → envoyer

---

## ✅ Check-list avant l'envoi

- [ ] Le jeu a été testé sur un vrai téléphone Android
- [ ] La clé `.jks` est sauvegardée à deux endroits distincts
- [ ] `keystore.properties` n'est PAS sur GitHub
- [ ] La page de politique de confidentialité est en ligne et accessible
- [ ] Icône 512×512 et image 1024×500 téléversées
- [ ] Au moins 4 captures d'écran téléversées
- [ ] Classification du contenu obtenue (PEGI 3)
- [ ] Formulaire Sécurité des données rempli (aucune collecte)
- [ ] Public cible incluant les enfants → aucune pub, aucun achat
- [ ] Descriptions FR + AR + EN renseignées
