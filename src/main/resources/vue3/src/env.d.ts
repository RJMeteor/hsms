/// <reference types="vite/client" />
interface ImportMetaEnv {
    readonly HIMS_REQUEST_URL:string;
    readonly HIMS_REQUEST_HEADERS_TOKEN_NAME:string;
}

interface ImportMeta {
    readonly env: ImportMetaEnv;
}