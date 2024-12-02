export function obtenerColoresDeProyecto(proyectoId: string) {
  switch (proyectoId) {
    case "a6e2167f-67a1-4f60-b9e9-6bae7bc3a15":
      return { borderColor: "#10b981", backgroundColor: "#a7f3d0" };
    case "1635b4ca-c091-472c-8b5a-cb3086d197b":
      return { borderColor: "#0e7490", backgroundColor: "#67e8f9" };
    case "0d4e3470-4dc8-4fda-a08f-bb822cb9fc7f":
      return { borderColor: "#ca8a04", backgroundColor: "#fef08a" };
    default:
      return { borderColor: "#6b7280", backgroundColor: "#e5e7eb" };
  }
}

