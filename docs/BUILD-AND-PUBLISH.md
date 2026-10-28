# Building and publishing MangaDT

## Android, Linux and Windows

The workflow in `.github/workflows/build-packages.yml` builds these downloadable packages:

- Android debug APK: `MangaDT-android-debug.apk`
- Linux x86-64 Debian package: `MangaDT-linux-amd64.deb`
- Windows x86-64 installer: `MangaDT-windows-x64.msi`

On pull requests and pushes to `main`, successful builds are uploaded to the workflow run as artifacts. Open **Actions**, select **Build Android, Linux and Windows packages**, then open the successful run's **Artifacts** section.

To publish stable direct-download links, push a version tag such as `v1.0.1`. The workflow builds all three packages and creates a GitHub Release with the packages and `SHA256SUMS`. Direct links follow this pattern:

- `https://github.com/SoufianoDev/MangaDT/releases/download/v1.0.1/MangaDT-android-debug.apk`
- `https://github.com/SoufianoDev/MangaDT/releases/download/v1.0.1/MangaDT-linux-amd64.deb`
- `https://github.com/SoufianoDev/MangaDT/releases/download/v1.0.1/MangaDT-windows-x64.msi`

For a test release without creating a version tag, run the workflow manually from the `main` branch and set **Publish all built packages as a GitHub Release** to true. This publishes a `build-<run>-<attempt>` release. The Android output is a debug APK for testing, not a Play Store release.

The desktop installers are built on their target operating systems. Linux output is a Debian package for Debian/Ubuntu-based distributions; Windows output is an MSI installer.

## Web app on GitHub Pages

The workflow in `.github/workflows/deploy-web-pages.yml` builds the Kotlin/Wasm web distribution and deploys it to GitHub Pages after a push to `main` or a manual run.

Before its first deployment, open **Settings → Pages** and set **Build and deployment → Source** to **GitHub Actions**. Once deployment succeeds, the project site should be available at:

`https://soufianodev.github.io/MangaDT/`

The existing web target still has platform-specific implementations that return empty manga-source results and do not implement browser file downloads or local-network sync. Successful publishing means the web UI is hosted; it does not mean those unsupported features work in a browser yet.
