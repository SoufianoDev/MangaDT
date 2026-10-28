#!/usr/bin/env bash
set -euo pipefail

repo_url="https://github.com/Kotatsu-Redo/kotatsu-parsers-redo.git"
commit="204c9ee032a2418fcda31fc1f856e88ced560051"
target=".ci/kotatsu-parsers-redo"

mkdir -p "$(dirname "$target")"
git init -q "$target"
git -C "$target" remote add origin "$repo_url"
git -C "$target" fetch --quiet --depth 1 origin "$commit"
git -C "$target" checkout --quiet --detach FETCH_HEAD

actual="$(git -C "$target" rev-parse HEAD)"
if [[ "$actual" != "$commit" ]]; then
  echo "Expected Kotatsu parser commit $commit, got $actual" >&2
  exit 1
fi

echo "Using pinned Kotatsu parser source: $actual"
