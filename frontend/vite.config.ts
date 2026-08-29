import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";

// The console is read-only: it fetches gate decisions and holdout results
// from the Spring Boot backend's REST API (see ../backend, MergeGateApplication)
// and renders them. It never writes anything back.
export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      "/api": "http://localhost:8080",
    },
  },
  test: {
    environment: "jsdom",
    globals: true,
    setupFiles: "./src/setupTests.ts",
  },
});
