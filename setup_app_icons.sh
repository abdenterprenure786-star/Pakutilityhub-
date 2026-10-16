#!/bin/bash
set -e

SRC="app/src/main/res/drawable/pak_rates_logo_1792169045693.jpg"
BASE="app/src/main/res"

# Standardize drawable name for foreground layer-list
cp "$SRC" app/src/main/res/drawable/pak_rates_logo.jpg
convert "$SRC" "PNG32:app/src/main/res/drawable/pak_rates_logo.png"

# Also copy to web directories so the website can use it
cp "$SRC" ./app_logo.jpg
convert "$SRC" -resize 128x128 "PNG32:./app_logo.png"
convert "$SRC" -resize 64x64 "PNG32:./favicon.png"
convert "$SRC" -resize 192x192 "PNG32:./icon-192.png"
convert "$SRC" -resize 512x512 "PNG32:./icon-512.png"

# Copy web icons into applet and docs
mkdir -p app/applet docs
cp ./app_logo.jpg app/applet/app_logo.jpg
cp ./app_logo.png app/applet/app_logo.png
cp ./favicon.png app/applet/favicon.png
cp ./app_logo.jpg docs/app_logo.jpg
cp ./app_logo.png docs/app_logo.png
cp ./favicon.png docs/favicon.png

# Process mipmap densities
for spec in mdpi:48 hdpi:72 xhdpi:96 xxhdpi:144 xxxhdpi:192; do
  dir="$BASE/mipmap-${spec%:*}"
  size="${spec#*:}"
  mkdir -p "$dir"
  rm -f "$dir/ic_launcher.webp" "$dir/ic_launcher_round.webp"
  convert "$SRC" -resize "${size}x${size}!" "PNG32:$dir/ic_launcher.png"
  radius=$((size / 2))
  convert "$SRC" -resize "${size}x${size}!" \
    \( -size "${size}x${size}" xc:none -fill white \
       -draw "circle $radius,$radius $radius,0" \) \
    -alpha set -compose DstIn -composite "PNG32:$dir/ic_launcher_round.png"
done

echo "Icons and mipmaps successfully generated!"
