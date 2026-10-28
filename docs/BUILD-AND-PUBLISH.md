# Building and publishing MangaDT

## Android, Linux and Windows

The workflow in `.github/workflows/build-packages.yml` creates these downloads:

- Android debug APK: `MangaDT-android-debug.apk`
- Linux x86-64 Debian package: `MangaDT-linux-amd64.deb`
- Linux x86-64 AppImage: `MangaDT-x86_64.AppImage`
- Windows x64 MSI installer: `MangaDT-windows-x64.msi`
- Windows x64 EXE installer: `MangaDT-windows-x64.exe`

On pull requests and pushes to `main`, successful builds are uploaded as workflow artifacts. Open **Actions**, select **Build Android, Linux and Windows packages**, and open the successful run's **Artifacts** section. Artifacts expire after 14 days.

The AppImage is a single-file Linux bundle with the application's Java runtime. Download it, make it executable, and launch it:

```bash
chmod +x MangaDT-x86_64.AppImage
./MangaDT-x86_64.AppImage
```

The `.deb` is for Debian/Ubuntu-based distributions. The AppImage and Linux DEB currently target x86-64. The Windows MSI and EXE are two installer formats for Windows x64; the EXE is an installer, not a portable single-file application.

To publish persistent download links, push a version tag such as `v1.0.1`. The workflow builds all five packages and creates a GitHub Release with the packages, per-file SHA-256 sidecars, and `SHA256SUMS`. Download links follow this pattern:

- `https://github.com/SoufianoDev/MangaDT/releases/download/v1.0.1/MangaDT-android-debug.apk`
- `https://github.com/SoufianoDev/MangaDT/releases/download/v1.0.1/MangaDT-linux-amd64.deb`
- `https://github.com/SoufianoDev/MangaDT/releases/download/v1.0.1/MangaDT-x86_64.AppImage`
- `https://github.com/SoufianoDev/MangaDT/releases/download/v1.0.1/MangaDT-windows-x64.msi`
- `https://github.com/SoufianoDev/MangaDT/releases/download/v1.0.1/MangaDT-windows-x64.exe`

For a test release without creating a version tag, run the workflow manually from the `main` branch and set **Publish all built packages as a GitHub Release** to true. This publishes a `build-<run>-<attempt>` release. The Android output is a debug APK for testing, not a Play Store release.

## Web app on GitHub Pages

The workflow in `.github/workflows/deploy-web-pages.yml` builds the Kotlin/Wasm web distribution and deploys it to GitHub Pages after a push to `main` or a manual run.

Before its first deployment, open **Settings → Pages** and set **Build and deployment → Source** to **GitHub Actions**. Once deployment succeeds, the project site should be available at:

`https://soufianodev.github.io/MangaDT/`

The existing web target still has platform-specific implementations that return empty manga-source results and do not implement browser manga-source queries, browser file downloads, or local-network sync. Publishing hosts the web UI but does not implement those unsupported features.
