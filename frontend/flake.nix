{
  inputs = {
    nixpkgs.url = "github:nixos/nixpkgs?ref=nixos-unstable";
  };

  outputs = {nixpkgs, ...}: let
    pkgs = nixpkgs.legacyPackages."aarch64-darwin";
  in {
    devShells."aarch64-darwin".default = pkgs.mkShell {
      packages = [
        pkgs.nodejs_22
        pkgs.tailwindcss-language-server
        pkgs.typescript-language-server
        pkgs.vscode-langservers-extracted
        pkgs.prettierd
      ];
    };
  };
}
