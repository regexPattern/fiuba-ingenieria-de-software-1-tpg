"use client";

import { useRouter } from "next/navigation";
import { eliminarCargaHoras } from "./action";

export default function BloqueCargaDeHoras({
  id,
  tareaNombre,
  cantidadHoras
}: {
  id: string;
  tareaNombre: string;
  cantidadHoras: number;
}) {
  const router = useRouter();

  const alturaRem = 6.0 + Math.min(cantidadHoras - 1, 23) * 0.75;

  async function handleEliminarCarga() {
    const confirmar = confirm(
      "¿Estás seguro de que deseas eliminar esta carga?"
    );
    if (!confirmar) return;

    try {
      // Llama a la server action para eliminar la carga de horas
      const resultado = await eliminarCargaHoras(id);

      if (resultado.exito) {
        alert(resultado.mensaje);
        router.refresh();
      } else {
        console.error("Error al eliminar la carga:", resultado.mensaje);
        alert(`Error: ${resultado.mensaje}`);
      }
    } catch (e) {
      console.error("Error inesperado al eliminar la carga:", e);
      alert(`Error inesperado: ${(e as Error).message}`);
    }
  }

  return (
    <div
      className="border border-emerald-500 bg-emerald-200 p-2 overflow-y-auto overflow-x-hidden flex flex-col"
      style={{ height: `${alturaRem}rem` }}
    >
      <span className="font-semibold underline">
        {cantidadHoras} hora{cantidadHoras > 1 && "s"}
      </span>
      <span className="text-sm">{tareaNombre}</span>
      <button
        onClick={handleEliminarCarga}
        className="text-red-600 font-bold mt-2 hover:underline"
      >
        Eliminar
      </button>
    </div>
  );
}
