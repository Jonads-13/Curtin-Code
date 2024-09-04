defmodule EngineMonitor do
  def latest([head|_]) do
    head
  end

  def new_reading(value, [head|tail]) do
    _ = [value] ++ [head|tail]
  end

  def max_reading([head|tail]) do
    max_reading(head, tail)
  end

  def max_reading(current_max, [head|tail]) do
    if current_max < head do
      max_reading(head, tail)
    else
      max_reading(current_max, tail)
    end
  end

  def max_reading(max, []) do
    max
  end

  def max([head, second | tail]) do
    if head < second do
      _ = [head|tail]--[head]
      max([second|tail])
    else
      _ = [head|tail]--[head]
      _ = [tail]++[head]
      max(tail)
    end
  end

  def max([max | []]) do
    max
  end

  def rising([h|t]) do
    rising(0, [h|t])
  end

  def rising(accumulator, [h,s|t]) do
    if s < h do
      rising(accumulator + 1, [s|t])
    else
      rising(accumulator)
    end
  end

  def rising(total) do
    total
  end

  def ris([]) do
    IO.puts("Still rising")
  end

  def danger?([h, s|t]) do
    if s - h >= 50 || h - s >= 50 do
      true
    else
      danger?([s|t])
    end
  end

  def danger?([_]) do
    false
  end

  def fire?(message) do
    if message |> String.downcase |> String.contains?("fire") do
      true
    else
      false
    end
  end

  def decode(message) do
    decode("", message |> String.split(" "))
  end

  def decode(message, [h|t]) do
    decode(message<>String.at(h,0), t)
  end

  def decode(message, []) do
    message
  end

end
