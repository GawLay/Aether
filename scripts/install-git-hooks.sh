#!/bin/sh
set -euo pipefail

echo "Installing git hooks..."

REPO_ROOT=$(git rev-parse --show-toplevel 2>/dev/null || echo "")
if [ -z "$REPO_ROOT" ]; then
  echo "Error: not inside a git repository."
  exit 1
fi

HOOKS_DIR="$REPO_ROOT/.git/hooks"
mkdir -p "$HOOKS_DIR"

cp -f "$REPO_ROOT/scripts/hooks/pre-commit" "$HOOKS_DIR/pre-commit"
cp -f "$REPO_ROOT/scripts/hooks/pre-push" "$HOOKS_DIR/pre-push"

chmod +x "$HOOKS_DIR/pre-commit" "$HOOKS_DIR/pre-push"

echo "Git hooks installed to $HOOKS_DIR"

git config core.hooksPath >/dev/null 2>&1 || true
