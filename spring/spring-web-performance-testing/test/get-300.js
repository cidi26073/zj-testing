import http from 'k6/http';
import { htmlReport } from "https://raw.githubusercontent.com/benc-uk/k6-reporter/main/dist/bundle.js";

export const options = {
  vus: 300,
  iterations: 900,
  duration: '30s'
};

export default function () {
  http.get('http://web:8080', {
    timeout: '10s'
  });
}

export function handleSummary(data) {
  return {
    "/output/summary.html": htmlReport(data),
  };
}